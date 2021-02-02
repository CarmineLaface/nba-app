package it.laface.schedule.api

import com.google.gson.Gson
import it.laface.networking.getApiService
import it.laface.networking.getClient
import it.laface.networking.getConverter
import it.laface.networking.getGson

object ScheduleApi {

    val gson: Gson
        get() = getGson(BuildConfig.NBA_API_DATE_FORMAT)

    val service: ScheduleService by lazy {
        val client = getClient()
        val converter = getConverter(gson)
        getApiService(
            baseUrl = BuildConfig.NBA_API_BASE_URL,
            converterFactory = converter,
            client = client
        )
    }
}
