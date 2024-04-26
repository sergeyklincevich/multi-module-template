package com.klinserg.news.news_details.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klinserg.news.data.ArticlesRepository
import com.klinserg.news.data.RequestResult
import com.klinserg.news.data.models.Article
import com.klinserg.news.news_details.usecases.GetArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    getArticleUseCase: GetArticleUseCase,
) : ViewModel() {

    val state: StateFlow<State> = getArticleUseCase(12)
        .map { it.toState() }
        .stateIn(viewModelScope, SharingStarted.Lazily, State.None)

    private fun RequestResult<Article>.toState(): State {
        return when (this) {
            is RequestResult.InProgress -> State.InProgress
            is RequestResult.Error -> State.Error(this.message)
            is RequestResult.Success -> State.Success(this.data)
            else -> State.None
        }
    }

    sealed class State {
        data object None : State()
        data object InProgress : State()
        class Error(val message: String?) : State()
        class Success(val article: Article) : State()
    }

}