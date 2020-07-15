package it.laface.network

import com.google.gson.GsonBuilder
import it.laface.api.NbaNews
import it.laface.api.NbaServices
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

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
}
