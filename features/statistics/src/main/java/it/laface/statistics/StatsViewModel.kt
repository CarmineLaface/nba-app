package it.laface.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.domain.network.NetworkResult
import it.laface.domain.datasource.StatsDataSource
import it.laface.domain.model.StatsSection
import it.laface.domain.navigation.Navigator
import it.laface.statistics.detail.LeadersPageProvider
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
        MutableStateFlow(ContentToShow.Loading)

    init {
        getStats()
    }

    private fun getStats() {
        viewModelScope.launch(jobDispatcher) {
            statsCallState.value = when (val response = statsDataSource.getLeaders()) {
                is NetworkResult.Success -> {
                    ContentToShow.Success(response.value)
                }
                is NetworkResult.Error ->
                    ContentToShow.Error
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
