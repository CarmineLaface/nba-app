package it.laface.domain.datasource

import it.laface.domain.NetworkResult
import it.laface.domain.model.Article

interface NewsDataSource {

    suspend fun getNews(limit: Int = 25, offset: Int = 0): NetworkResult<List<Article>>
}
