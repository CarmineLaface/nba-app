package it.laface.networking

import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonToken
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.OutputStreamWriter
import java.lang.reflect.Type
import java.nio.charset.Charset

class GsonConverterFactory(
    private val gson: Gson = Gson()
): Converter.Factory() {

    companion object {

        private val MEDIA_TYPE = "application/json; charset=UTF-8".toMediaType()
        private val UTF_8 = Charset.forName("UTF-8")
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        return internalResponseBodyConverter(
            gson.getAdapter(TypeToken.get(type))
        )
    }

    private fun <T: Any> internalResponseBodyConverter(
        adapter: TypeAdapter<T>
    ): Converter<ResponseBody, T> =
        Converter { value ->
            val jsonReader = gson.newJsonReader(value.charStream())
            value.use {
                val result = adapter.read(jsonReader)
                if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                    throw JsonIOException("JSON document was not fully consumed.")
                }
                result
            }
        }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody> {
        return internalRequestBodyConverter(
            gson.getAdapter(TypeToken.get(type))
        )
    }

    private fun <T: Any> internalRequestBodyConverter(
        adapter: TypeAdapter<T>
    ): Converter<T, RequestBody> =
        Converter { value ->
            val buffer = Buffer()
            val writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
            val jsonWriter = gson.newJsonWriter(writer)
            adapter.write(jsonWriter, value as T)
            jsonWriter.close()
            buffer.readByteString().toRequestBody(MEDIA_TYPE)
        }
}