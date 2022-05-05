package com.yakupsd.newsapp.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class GetNewsResponse(
    @SerializedName("status")
    val status:String?,
    @SerializedName("totalResults")
    val totalResults : String?,
    @SerializedName("code")
    val code : String?,
    @SerializedName("message")
    val message : String?,
    @SerializedName("articles")
    val article : ArrayList<News>
)
@Parcelize
data class Source(
    @SerializedName("id")
    val newsId: String? = null,
    @SerializedName("name")
    val newsName: String?,
):Parcelable

@Parcelize
data class News(
    val source : Source,
    @SerializedName("author")
    val newsAuthor: String?,
    @SerializedName("title")
    val newsTitle: String?,
    @SerializedName("description")
    val newsDescription: String?,
    @SerializedName("url")
    val newsUrl: String?,
    @SerializedName("urlToImage")
    val newsUrlToImage: String?,
    @SerializedName("publishedAt")
    val newsPublishedAt: String?,
    @SerializedName("content")
    val newsContent: String?=null,
    ): Parcelable


@Entity(
    tableName = "news"
)
data class NewsDto(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    val newsImage:String?,
    val newsDescription: String?,
    val newsTitle:String?,
    val newsUrl:String?,
    val sourceName:String?,
    val author:String?,
    val publishedAt:String?
)


