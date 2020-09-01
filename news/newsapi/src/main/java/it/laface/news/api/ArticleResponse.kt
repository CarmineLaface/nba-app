package it.laface.news.api

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ArticleResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("posts") val posts: List<Post>
)

data class Post(
    @SerializedName("title") val title: String,
    @SerializedName("date") val date: Date,
    @SerializedName("content") val content: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("meta") val meta: Meta
)

data class Meta(
    @SerializedName("beyondthenumber-link") val link: String
)
