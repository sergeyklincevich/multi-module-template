package com.klinserg.news.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.klinserg.news.db.models.ArticleDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    suspend fun getAll(): List<ArticleDBO>

    @Query("SELECT * FROM articles")
    fun observeAll(): Flow<List<ArticleDBO>>

    @Insert
    suspend fun insertAll(articles: List<ArticleDBO>)

    @Delete
    fun delete(article: ArticleDBO)

    @Query("DELETE FROM articles")
    fun deleteAll()
}