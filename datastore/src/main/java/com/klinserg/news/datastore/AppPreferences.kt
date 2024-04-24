package com.klinserg.news.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

enum class SortOrder {
    NONE,
    BY_NAME,
    BY_DATE,
}

data class UserPreferences(
    val isEnabled: Boolean,
    val sortOrder: SortOrder,
)

class AppPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private object PreferencesKeys {
        val SORT_ORDER = stringPreferencesKey("sort_order")
        val IS_ENABLED = booleanPreferencesKey("is_enabled")
    }

    val isEnabledFlow: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.IS_ENABLED] ?: false
        }

    suspend fun setEnabled(flag: Boolean) {
        dataStore.edit { settings ->
            settings[PreferencesKeys.IS_ENABLED] = flag
        }
    }

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data.map(::mapUserPreferences)

    private fun mapUserPreferences(preferences: Preferences): UserPreferences {
        val sortOrder =
            SortOrder.valueOf(preferences[PreferencesKeys.SORT_ORDER] ?: SortOrder.NONE.name)

        val isEnabled = preferences[PreferencesKeys.IS_ENABLED] ?: false
        return UserPreferences(isEnabled, sortOrder)
    }
}