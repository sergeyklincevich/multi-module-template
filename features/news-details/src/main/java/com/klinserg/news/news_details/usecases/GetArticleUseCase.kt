package com.klinserg.news.news_details.usecases

import com.klinserg.news.data.ArticlesRepository
import com.klinserg.news.data.RequestResult
import com.klinserg.news.data.models.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(
    private val repository: ArticlesRepository,
) {

    operator fun invoke(articleId: Int): Flow<RequestResult<Article>> {
        return repository.getLocalArticle(articleId);
    }
}