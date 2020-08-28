package it.laface.networking

import com.google.gson.GsonBuilder
import it.laface.networking.ApiHelper.NBA_API_BASE_URL
import it.laface.networking.ApiHelper.NBA_API_DATE_FORMAT
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

inline fun <reified T> getApiService(
    baseUrl: String = NBA_API_BASE_URL,
    converterFactory: Converter.Factory,
    client: OkHttpClient
): T {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .client(client)
        .build()
        .create(T::class.java)
}

fun getClient(vararg interceptors: Interceptor) : OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(IOExceptionInterceptor())
        .apply {
            for (interceptor in interceptors) {
                addInterceptor(interceptor)
            }
        }
        .build()

fun getConverter(
    dateFormat: String? = NBA_API_DATE_FORMAT,
    adapterInfo: AdapterInfo? = null
): GsonConverterFactory =
    GsonConverterFactory.create(
        GsonBuilder()
            .setDateFormat(dateFormat)
            .apply {
                if (adapterInfo != null) {
                    registerTypeAdapter(adapterInfo.first, adapterInfo.second)
                }
            }
            .create()
    )

typealias AdapterInfo = Pair<Type, Any>
