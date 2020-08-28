package it.laface.statistics.detail

import androidx.lifecycle.ViewModel
import it.laface.navigation.Navigator
import it.laface.stats.domain.StatsSection

class LeadersViewModel(
    val section: StatsSection,
    private val navigator: Navigator
) : ViewModel() {

    fun onBackClicked() {
        navigator.navigateBack()
    }
}
