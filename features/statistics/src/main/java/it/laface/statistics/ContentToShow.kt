package it.laface.statistics

import it.laface.domain.model.StatsGroup

sealed class ContentToShow {

    object Loading : ContentToShow()
    object Error : ContentToShow()
    data class Success(val statsGroup: List<StatsGroup>) : ContentToShow()
}
