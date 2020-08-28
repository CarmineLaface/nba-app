package it.laface.networking

import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import java.io.InterruptedIOException
import java.net.UnknownHostException

class IOExceptionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return try {
            chain.proceed(request)
        } catch (exception: InterruptedIOException) {
            request.generateException(exception, TIMEOUT_STATUS_CODE)
        } catch (exception: UnknownHostException) {
            request.generateException(exception, MISSING_CONNECTION_STATUS_CODE)
        } catch (exception: IOException) {
            request.generateException(exception, GENERIC_EXCEPTION_STATUS_CODE)
        }
    }

    companion object {
        const val TIMEOUT_STATUS_CODE: Int = 497
        const val MISSING_CONNECTION_STATUS_CODE: Int = 498
        const val GENERIC_EXCEPTION_STATUS_CODE: Int = 499
    }
}

private fun Request.generateException(exception: IOException, statusCode: Int): Response {
    val message = exception.message ?: exception.toString()
    return Response.Builder()
        .code(statusCode)
        .request(this)
        .protocol(Protocol.HTTP_2)
        .message(message)
        .body(message.toResponseBody())
        .build()
}
