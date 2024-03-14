package com.klinserg.news.ui.usecases

import com.klinserg.news.data.ArticlesRepository
import com.klinserg.news.data.RequestResult
import com.klinserg.news.data.models.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllArticlesUseCase @Inject constructor(private val repository: ArticlesRepository) {

    operator fun invoke(query: String): Flow<RequestResult<List<Article>>> {
        return repository.getAllArticles(query)
    }

}
