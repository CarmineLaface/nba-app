package it.laface.news

import it.laface.news.domain.Article

sealed class ContentToShow {

    object Loading : ContentToShow()
    object Error : ContentToShow()
    data class Success(val filteredList: List<Article>) : ContentToShow()
}
