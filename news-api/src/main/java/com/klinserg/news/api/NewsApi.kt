package com.klinserg.news.api

import androidx.annotation.IntRange
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.klinserg.news.api.models.ArticleDTO
import com.klinserg.news.api.models.Language
import com.klinserg.news.api.models.ResponseDTO
import com.klinserg.news.api.models.SortBy
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date


interface NewsApi {

    @GET("everything")
    suspend fun everything(
        @Query("q") query: String? = null,
        @Query("from") from: Date? = null,
        @Query("to") to: Date? = null,
        @Query("language") languages: List<@JvmSuppressWildcards Language>? = null,
        @Query("sortBy") sortBy: SortBy = SortBy.PUBLISHED_AT,
        @Query("pageSize") @IntRange(from = 0, to = 100) pageSize: Int = 100,
        @Query("page") @IntRange(from = 1) page: Int = 1,
    ): Result<ResponseDTO<ArticleDTO>>
}

fun retrofitNewsApi(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null,
): NewsApi {
    return retrofit(baseUrl, apiKey, okHttpClient, Json).create()
}

private fun retrofit(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient?,
    json: Json = Json,
): Retrofit {
    val contentType = "application/json".toMediaType()
    val jsonConverterFactory = json.asConverterFactory(contentType)
    val modifiedOkHttpClient = okHttpClient(okHttpClient, apiKey)

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .addConverterFactory(jsonConverterFactory)
        .client(okHttpClient(modifiedOkHttpClient, apiKey))
        .build()
}

private fun okHttpClient(
    okHttpClient: OkHttpClient?,
    apiKey: String,
): OkHttpClient {
    return (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(NewsApiKeyInterceptor(apiKey))
        .build()
}

