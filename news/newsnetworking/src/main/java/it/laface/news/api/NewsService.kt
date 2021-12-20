package it.laface.news.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("/wp-json/statscms/v1/type/beyondthenumber/")
    suspend fun getNews(
        @Query(value = "limit") limit: Int = 25,
        @Query(value = "offset") offset: Int = 0
    ): Response<ArticleResponse>

    companion object {

        internal const val BASE_URL: String = "https://stats-prod.nba.com"
    }
}
