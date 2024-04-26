package com.klinserg.news.news_search.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klinserg.news.data.RequestResult
import com.klinserg.news.data.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
//    getAllArticlesUseCase: GetAllArticlesUseCase,
) : ViewModel() {

    //first state whether the search is happening or not
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    //second state the text typed by the user
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    //third state the list to be filtered
    private val _newsList = MutableStateFlow(emptyList<Article>())
    val newsList = searchText
        .combine(_newsList) { text, news ->
            if (text.isBlank()) {
                news
            }
//            news.filter { news ->
//                news.uppercase().contains(text.trim().uppercase())
//            }
            news
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _newsList.value
        )
//    val state: StateFlow<State> = getAllArticlesUseCase(query = "android")
//        .map { it.toState() }
//        .stateIn(viewModelScope, SharingStarted.Lazily, State.None)

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