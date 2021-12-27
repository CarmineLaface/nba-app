package it.laface.networking

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

inline fun <reified T> getApiService(
    baseUrl: String,
    converterFactory: Converter.Factory,
    client: OkHttpClient.Builder
): T {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .client(client.build())
        .build()
        .create(T::class.java)
}

fun getClient(): OkHttpClient.Builder =
    OkHttpClient.Builder()
        .addInterceptor(IOExceptionInterceptor())

fun getGson(
    dateFormat: String? = null,
    adapterInfo: AdapterInfo? = null
): Gson =
    GsonBuilder()
        .apply {
            if (dateFormat != null) {
                setDateFormat(dateFormat)
            }
            adapterInfo?.let { (type, typeAdapter) ->
                registerTypeAdapter(type, typeAdapter)
            }
        }
        .create()

fun getConverterFactory(gson: Gson): Converter.Factory =
    GsonConverterFactory(gson)

typealias AdapterInfo = Pair<Type, Any>
