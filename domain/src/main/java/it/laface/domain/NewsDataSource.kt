package it.laface.domain

interface NewsDataSource {

    suspend fun getNews(limit: Int = 25, offset: Int = 0): NetworkResult<List<Article>>
}
