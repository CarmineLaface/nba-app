package it.laface.statistics.detail

import androidx.lifecycle.ViewModel
import it.laface.stats.domain.StatsSection
import it.laface.navigation.Navigator

class LeadersViewModel(
    val section: StatsSection,
    private val navigator: Navigator
) : ViewModel() {

    fun onBackClicked() {
        navigator.navigateBack()
    }
}
