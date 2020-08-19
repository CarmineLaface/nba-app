package it.laface.domain.datasource

import it.laface.domain.model.Article
import it.laface.domain.network.NetworkResult

interface NewsDataSource {

    suspend fun getNews(limit: Int = 25, offset: Int = 0): NetworkResult<List<Article>>
}
