package it.laface.network

import com.google.gson.GsonBuilder
import it.laface.api.NbaNews
import it.laface.api.NbaServices
import it.laface.api.NbaStats
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit.SECONDS

object NetworkManager {

    private fun getConverter(dateFormat: String): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder()
                .setDateFormat(dateFormat)
                .create()
        )
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(IOExceptionInterceptor())
            .callTimeout(SECONDS_TIMEOUT, SECONDS)
            .build()
    }

    fun getNbaNewsApi(): NbaNews {
        return Retrofit.Builder()
            .baseUrl(NbaNews.BASE_URL)
            .addConverterFactory(getConverter(NbaNews.DATE_FORMAT))
            .client(getClient())
            .build()
            .create()
    }

    fun getNbaApi(): NbaServices {
        return Retrofit.Builder()
            .baseUrl(NbaServices.BASE_URL)
            .addConverterFactory(getConverter(NbaServices.DATE_FORMAT))
            .client(getClient())
            .build()
            .create()
    }

    fun getStatsApi(): NbaStats {
        return Retrofit.Builder()
            .baseUrl(NbaStats.BASE_URL)
            .addConverterFactory(getConverter(NbaStats.DATE_FORMAT))
            .client(getClient())
            .build()
            .create()
    }

    private const val SECONDS_TIMEOUT = 10L
}
