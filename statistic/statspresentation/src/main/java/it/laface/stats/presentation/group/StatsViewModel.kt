package it.laface.stats.presentation.group

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.base.NetworkResult
import it.laface.common.ContentListToShow
import it.laface.common.ContentToShow
import it.laface.navigation.Navigator
import it.laface.stats.domain.LeadersPageProvider
import it.laface.stats.domain.StatsDataSource
import it.laface.stats.domain.StatsSection
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StatsViewModel(
    private val navigator: Navigator,
    private val leadersPageProvider: LeadersPageProvider,
    private val statsDataSource: StatsDataSource,
    private val jobDispatcher: CoroutineDispatcher
) : ViewModel() {

    val statsCallState: MutableStateFlow<ContentListToShow<StatsSection>> =
        MutableStateFlow(ContentToShow.Loading)

    init {
        getStats()
    }

    private fun getStats() {
        viewModelScope.launch(jobDispatcher) {
            statsCallState.value =
                when (val response = statsDataSource.getLeaders()) {
                    is NetworkResult.Success ->
                        ContentToShow.Success(response.value)
                    is NetworkResult.Failure ->
                        ContentToShow.Error
                }
        }
    }

    fun onStatsClicked(section: StatsSection) {
        navigator.navigateTo(leadersPageProvider.getLeadersPage(section))
    }

    fun navigateBack() {
        navigator.navigateBack()
    }
}
