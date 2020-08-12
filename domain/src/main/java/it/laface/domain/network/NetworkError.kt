package it.laface.domain.network

sealed class NetworkError {
    object MissingConnection : NetworkError()
    object Timeout : NetworkError()
    data class ServerError(val message: String, val code: Int) : NetworkError()
    data class ClientError(val message: String, val code: Int) : NetworkError()
    data class UnknownError(val message: String) : NetworkError()
}
