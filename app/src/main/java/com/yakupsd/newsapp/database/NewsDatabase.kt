package com.yakupsd.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yakupsd.newsapp.model.NewsDto

@Database(
    entities = [NewsDto::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun getArticleDao():NewsDAO

    companion object{

        @Volatile
        private var instance : NewsDatabase?=null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "article_db.db"
            ).build()
    }
}