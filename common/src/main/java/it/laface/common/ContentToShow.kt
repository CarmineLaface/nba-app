package it.laface.common

sealed class ContentToShow<out T> {

    object Loading : ContentToShow<Nothing>()
    object Error : ContentToShow<Nothing>()
    object Placeholder : ContentToShow<Nothing>()
    data class Success<out T>(val contentList: List<T>) : ContentToShow<T>()
}
