package com.klinserg.news.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.klinserg.news.datastore_proto.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

//    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private val Context.dataStoreProto: DataStore<UserPreferences> by dataStore(
        fileName = "user_preferences.pb",
        serializer = UserPreferencesSerializer()
    )

//    @Provides
//    @Singleton
//    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
//        return context.dataStore
//    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<UserPreferences> {
        return context.dataStoreProto
    }
}