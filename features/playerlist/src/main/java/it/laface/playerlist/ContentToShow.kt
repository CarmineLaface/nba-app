package it.laface.playerlist

import it.laface.domain.model.Player

sealed class ContentToShow {

    object Loading : ContentToShow()
    object Error : ContentToShow()
    object Placeholder : ContentToShow()
    data class Success(val filteredList: List<Player>) : ContentToShow()
}
