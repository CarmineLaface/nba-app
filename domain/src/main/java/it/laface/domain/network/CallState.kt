package it.laface.domain.network

sealed class CallState<out T> {

    object NotStarted : CallState<Nothing>()

    object InProgress : CallState<Nothing>()

    data class Success<out T>(val result: T) : CallState<T>()

    data class Error(val error: NetworkError) : CallState<Nothing>()
}
