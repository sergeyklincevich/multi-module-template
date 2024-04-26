package com.klinserg.news.data

import com.klinserg.news.api.NewsApi
import com.klinserg.news.api.models.ArticleDTO
import com.klinserg.news.api.models.ResponseDTO
import com.klinserg.news.core.Logger
import com.klinserg.news.core.TAG
import com.klinserg.news.data.models.Article
import com.klinserg.news.db.NewsDatabase
import com.klinserg.news.db.models.ArticleDBO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ArticlesRepository @Inject constructor(
    private val api: NewsApi,
    private val db: NewsDatabase,
    private val logger: Logger,
) {

    fun getAllArticles(
        query: String,
        mergeStrategy: MergeStrategy<RequestResult<List<Article>>> = RequestResultMergeStrategy(),
    ): Flow<RequestResult<List<Article>>> {
        val localData: Flow<RequestResult<List<Article>>> = getLocalArticles()
        val remoteData: Flow<RequestResult<List<Article>>> = getRemoteArticles(query)

        return localData.combine(remoteData) { dbo, dto ->
            mergeStrategy.merge(dbo, dto)
        }
    }

    private fun getLocalArticles(): Flow<RequestResult<List<Article>>> {
        val dbData = flow { emit(db.articleDao.getAll()) }
            .map { RequestResult.Success(it) }
            .catch {
                RequestResult.Error(message = it.message)
                logger.e(TAG, "Error during getting data from DB: ${it.message}")
            }
        val start = flow<RequestResult<List<ArticleDBO>>> { emit(RequestResult.InProgress) }

        return merge(start, dbData)
            .map { result -> result.map { dbos -> dbos.map { it.toArticle() } } }
    }

    private suspend fun saveArticlesToDatabase(articles: List<ArticleDTO>) {
        db.articleDao.insertAll(articles.map { it.toArticleDBO() })
    }

    private fun getRemoteArticles(query: String): Flow<RequestResult<List<Article>>> {
        val apiData = flow { emit(api.everything(query)) }
            .onEach { result ->
                when {
                    result.isSuccess -> saveArticlesToDatabase(result.getOrThrow().articles.filter { !it.author.isNullOrEmpty() })
                    result.isFailure ->
                        logger.e(
                            TAG,
                            "Error during getting data from server: ${result.exceptionOrNull()}"
                        )
                }
            }
            .map { it.toRequestResult() }

        val start =
            flow<RequestResult<ResponseDTO<ArticleDTO>>> { emit(RequestResult.InProgress) }

        return merge(start, apiData).map { result ->
            result.map { response ->
                response.articles.map { it.toArticle() }.filter { !it.author.isNullOrEmpty() }
            }
        }
    }

    fun getLocalArticle(articleId: String): Flow<RequestResult<Article>> {
        val dbData = flow { emit(db.articleDao.getArticle(articleId)) }
            .map {
                if (it == null) {
                    RequestResult.Error("There is no Article with such ID in Local DB")
                } else RequestResult.Success(it)
            }
            .catch {
                RequestResult.Error(message = it.message)
                logger.e(TAG, "Error during getting data from DB: ${it.message}")
            }
        val start = flow<RequestResult<ArticleDBO>> { emit(RequestResult.InProgress) }

        return merge(start, dbData)
            .map { result -> result.map { it.toArticle() } }
    }
}

