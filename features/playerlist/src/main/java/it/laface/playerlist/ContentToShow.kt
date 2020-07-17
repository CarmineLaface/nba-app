package it.laface.playerlist

import it.laface.domain.model.PlayerModel

sealed class ContentToShow {

    object Loading : ContentToShow()
    object Error : ContentToShow()
    object Placeholder : ContentToShow()
    data class Success(val filteredList: List<PlayerModel>) : ContentToShow()
}
