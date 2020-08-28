package it.laface.statistics.group

import it.laface.stats.domain.StatsSection

sealed class ContentToShow {

    object Loading : ContentToShow()
    object Error : ContentToShow()
    data class Success(val statsSections: List<StatsSection>) : ContentToShow()
}
