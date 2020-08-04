package it.laface.statistics

import it.laface.domain.model.StatsSection

sealed class ContentToShow {

    object Loading : ContentToShow()
    object Error : ContentToShow()
    data class Success(val statsSections: List<StatsSection>) : ContentToShow()
}
