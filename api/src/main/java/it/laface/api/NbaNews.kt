package it.laface.api

import it.laface.api.models.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NbaNews {

    @GET("/wp-json/statscms/v1/type/beyondthenumber/")
    suspend fun getNews(
        @Query(value = "limit") limit: Int = 25,
        @Query(value = "offset") offset: Int = 0
    ): Response<ArticleResponse>

    companion object {

        const val BASE_URL: String = "https://stats-prod.nba.com"
        const val DATE_FORMAT: String = "yyyy-MM-dd HH:mm:ss"
    }
}
