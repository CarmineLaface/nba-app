package it.laface.news.api

import com.google.gson.Gson
import it.laface.networking.getApiService
import it.laface.networking.getClient
import it.laface.networking.getConverter
import it.laface.networking.getGson

object NewsApi {

    val gson: Gson
        get() = getGson()

    val service: NewsService by lazy {
        val client = getClient()
        val converter = getConverter(gson)
        getApiService(NewsService.BASE_URL, converter, client)
    }
}
