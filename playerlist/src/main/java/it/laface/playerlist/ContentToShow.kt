package it.laface.playerlist

sealed class ContentToShow {

    object Loading : ContentToShow()
    object Error : ContentToShow()
    object Placeholder : ContentToShow()
    data class Success(val filteredList: List<it.laface.domain.PlayerModel>) : ContentToShow()
}
