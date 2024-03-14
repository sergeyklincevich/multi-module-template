package com.klinserg.news.template

import android.content.Context
import com.klinserg.news.api.NewsApi
import com.klinserg.news.api.retrofitNewsApi
import com.klinserg.news.core.AndroidLogcatLogger
import com.klinserg.news.core.AppDispatcher
import com.klinserg.news.core.Logger
import com.klinserg.news.db.NewsDatabase
import com.klinserg.news.db.getDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(okHttpClient: OkHttpClient): NewsApi {
        return retrofitNewsApi(
            baseUrl = BuildConfig.NEWS_API_BASE_URL,
            apiKey = BuildConfig.NEWS_API_KEY,
            okHttpClient = okHttpClient
        )
    }

    @Provides
    @Singleton
    fun provideAppCoroutineDispatcher(): AppDispatcher = AppDispatcher()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase = getDatabase(context)

    @Provides
    fun provideAndroidLogcatLogger(): Logger = AndroidLogcatLogger()

}