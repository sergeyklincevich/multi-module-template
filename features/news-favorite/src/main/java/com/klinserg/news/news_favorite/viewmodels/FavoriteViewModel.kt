package com.klinserg.news.news_favorite.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klinserg.news.datastore.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val preferences: AppPreferences,
) : ViewModel() {

    val state: StateFlow<Boolean> = preferences.isEnabledFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    fun changeState() {
        viewModelScope.launch {
            preferences.setEnabled(!state.value)
        }
    }
}