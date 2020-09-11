package it.laface.common

sealed class ContentToShow<out T> {

    object Loading : ContentToShow<Nothing>()
    object Error : ContentToShow<Nothing>()
    object Placeholder : ContentToShow<Nothing>()
    data class Success<out T>(val content: T) : ContentToShow<T>()
}

typealias ContentListToShow<T> = ContentToShow<List<T>>
