package it.laface.news

import com.google.gson.Gson
import it.laface.api.models.ArticleResponse
import java.io.InputStreamReader

fun Any.openResources(fileName: String): InputStreamReader =
    InputStreamReader(javaClass.classLoader!!.getResourceAsStream(fileName))

fun Any.getArticleResponse(): ArticleResponse {
    return Gson().fromJson(openResources("news.json"), ArticleResponse::class.java)
}
