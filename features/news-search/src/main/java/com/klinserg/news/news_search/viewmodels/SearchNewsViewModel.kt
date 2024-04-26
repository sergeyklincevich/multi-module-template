package com.klinserg.news.news_search.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klinserg.news.data.ArticlesRepository
import com.klinserg.news.data.RequestResult
import com.klinserg.news.data.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

private const val SEARCH_QUERY_MIN_LENGTH = 3

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val repository: ArticlesRepository,
) : ViewModel() {

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    val newsList = searchText
        .flatMapLatest { query ->
            if (query.length < SEARCH_QUERY_MIN_LENGTH) {
                flowOf(RequestResult.Success(emptyList()))
            } else {
                repository.getAllArticles(query)
            }
        }
        .map { it.toState() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = State.None
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }

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