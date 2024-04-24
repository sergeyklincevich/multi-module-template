package com.klinserg.news.datastore

import androidx.datastore.core.DataStore
import com.klinserg.news.datastore_proto.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppProtoPreferences @Inject constructor(
    private val dataStore: DataStore<UserPreferences>,
) {

    val isEnabledFlow: Flow<Boolean> = dataStore.data
        .map { it.isEnabled }

    suspend fun setEnabled(flag: Boolean) {
        dataStore.updateData { it.toBuilder().setIsEnabled(flag).build() }
    }

    val userPreferencesFlow: Flow<CommonPreferences> = dataStore.data.map(::mapUserPreferences)

    private fun mapUserPreferences(preferences: UserPreferences): CommonPreferences {
        val sortOrder = SortOrder.valueOf(preferences.sortOrder ?: SortOrder.NONE.name)
        val isEnabled = preferences.isEnabled
        return CommonPreferences(isEnabled, sortOrder)
    }
}