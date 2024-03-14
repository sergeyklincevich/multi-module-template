package com.klinserg.news.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.klinserg.news.db.dao.ArticleDao
import com.klinserg.news.db.models.ArticleDBO
import com.klinserg.news.db.utils.Converters

class NewsDatabase internal constructor(private val database: NewsRoomDatabase) {
    val articleDao: ArticleDao
        get() = database.articlesDao()
}

@Database(entities = [ArticleDBO::class], version = 1)
@TypeConverters(Converters::class)
abstract class NewsRoomDatabase : RoomDatabase() {
    abstract fun articlesDao(): ArticleDao
}

fun getDatabase(context: Context): NewsDatabase {
    val roomDatabase = Room.databaseBuilder(
        context.applicationContext,
        NewsRoomDatabase::class.java,
        "db-news"
    ).build()
    return NewsDatabase(roomDatabase)
}
