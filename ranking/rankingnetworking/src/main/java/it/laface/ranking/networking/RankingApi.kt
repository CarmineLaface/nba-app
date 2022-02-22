package it.laface.ranking.networking

import com.google.gson.Gson
import it.laface.networking.ApiHelper
import it.laface.networking.getApiService
import it.laface.networking.getConverterFactory
import it.laface.networking.getGson

object RankingApi {

    val gson: Gson
        get() = getGson(ApiHelper.DATE_FORMAT)

    val service: RankingService by lazy {
        val converter = getConverterFactory(gson)
        getApiService(
            converterFactory = converter,
        )
    }
}
