package it.laface.news.domain

import it.laface.base.NetworkResult

interface NewsDataSource {

    suspend fun getNews(): NetworkResult<List<Article>>
}
