package it.laface.schedule

import it.laface.domain.model.Game

sealed class ContentToShow {

    object Loading : ContentToShow()
    object Error : ContentToShow()
    object Placeholder : ContentToShow()
    data class Success(val filteredList: List<Game>) : ContentToShow()
}
