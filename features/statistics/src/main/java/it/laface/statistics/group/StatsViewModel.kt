package it.laface.statistics.group

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.base.NetworkResult
import it.laface.navigation.Navigator
import it.laface.statistics.group.ContentToShow.Error
import it.laface.statistics.group.ContentToShow.Loading
import it.laface.statistics.group.ContentToShow.Success
import it.laface.stats.domain.LeadersPageProvider
import it.laface.stats.domain.StatsDataSource
import it.laface.stats.domain.StatsSection
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Suppress("EXPERIMENTAL_API_USAGE")
class StatsViewModel(
    private val navigator: Navigator,
    private val leadersPageProvider: LeadersPageProvider,
    private val statsDataSource: StatsDataSource,
    private val jobDispatcher: CoroutineDispatcher
) : ViewModel() {

    val statsCallState: MutableStateFlow<ContentToShow> =
        MutableStateFlow(Loading)

    init {
        getStats()
    }

    private fun getStats() {
        viewModelScope.launch(jobDispatcher) {
            statsCallState.value = when (val response = statsDataSource.getLeaders()) {
                is NetworkResult.Success -> {
                    Success(response.value)
                }
                is NetworkResult.Error ->
                    Error
            }
        }
    }

    fun onStatsClicked(section: StatsSection) {
        navigator.navigateForward(leadersPageProvider.getLeadersPage(section))
    }

    fun navigateBack() {
        navigator.navigateBack()
    }
}
