package it.laface.ranking.networking

import com.google.gson.Gson
import it.laface.networking.getApiService
import it.laface.networking.getClient
import it.laface.networking.getConverterFactory
import it.laface.networking.getGson

object RankingApi {

    val gson: Gson
        get() = getGson(BuildConfig.NBA_API_DATE_FORMAT)

    val service: RankingService by lazy {
        val client = getClient()
        val converter = getConverterFactory(gson)
        getApiService(
            baseUrl = BuildConfig.NBA_API_BASE_URL,
            converterFactory = converter,
            client = client
        )
    }
}
