package it.laface.news.api

import com.google.gson.Gson
import it.laface.networking.getApiService
import it.laface.networking.getConverterFactory
import it.laface.networking.getGson

object NewsApi {

    val gson: Gson
        get() = getGson()

    val service: NewsService by lazy {
        val converter = getConverterFactory(gson)
        getApiService(NewsService.BASE_URL, converter)
    }
}
