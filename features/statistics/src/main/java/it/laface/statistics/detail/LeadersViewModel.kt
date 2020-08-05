package it.laface.statistics.detail

import androidx.lifecycle.ViewModel
import it.laface.domain.model.StatsSection
import it.laface.domain.navigation.Navigator

class LeadersViewModel(
    val section: StatsSection,
    private val navigator: Navigator
) : ViewModel() {

    fun onBackClicked() {
        navigator.navigateBack()
    }
}
