package com.yakupsd.newsapp.database

import androidx.room.*
import com.yakupsd.newsapp.model.NewsDto

@Dao
interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: NewsDto):Long

    @Query("SELECT * FROM news")
    suspend fun getAllArticles(): List<NewsDto>

    @Delete
    suspend fun deleteArticle(article: NewsDto)
}