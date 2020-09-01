package it.laface.news.domain

import it.laface.base.NetworkResult

interface NewsDataSource {

    suspend fun getNews(limit: Int = 25, offset: Int = 0): NetworkResult<List<Article>>
}
