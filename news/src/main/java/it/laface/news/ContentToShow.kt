package it.laface.news

sealed class ContentToShow {

    object Loading : ContentToShow()
    object Error : ContentToShow()
    data class Success(val filteredList: List<it.laface.domain.Article>) : ContentToShow()
}
