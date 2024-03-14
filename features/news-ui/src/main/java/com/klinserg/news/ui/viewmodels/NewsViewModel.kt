package com.klinserg.news.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klinserg.news.data.RequestResult
import com.klinserg.news.data.models.Article
import com.klinserg.news.ui.usecases.GetAllArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    getAllArticlesUseCase: GetAllArticlesUseCase,
) : ViewModel() {

    val state: StateFlow<State> = getAllArticlesUseCase(query = "android")
        .map { it.toState() }
        .stateIn(viewModelScope, SharingStarted.Lazily, State.None)

    private fun RequestResult<List<Article>>.toState(): State {
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
        class Success(val articles: List<Article>) : State()
    }

}