package it.laface.news.api

import it.laface.networking.getApiService
import it.laface.networking.getClient
import it.laface.networking.getConverter

object NewsApi {

    val service: NewsService by lazy {
        val client = getClient()
        val converter = getConverter(NewsService.DATE_FORMAT)
        getApiService(NewsService.BASE_URL, converter, client)
    }
}
