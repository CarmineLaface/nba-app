package it.laface.ranking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.domain.datasource.RankingDataSource
import it.laface.domain.model.RankedTeam
import it.laface.domain.model.RankingLists
import it.laface.domain.navigation.Navigator
import it.laface.domain.navigation.TeamPageProvider
import it.laface.domain.network.CallState
import it.laface.domain.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Suppress("EXPERIMENTAL_API_USAGE")
class RankingViewModel(
    private val dataSource: RankingDataSource,
    private val jobDispatcher: CoroutineDispatcher,
    private val teamPageProvider: TeamPageProvider,
    private val navigator: Navigator
) : ViewModel() {

    val rankingListsCallState: MutableStateFlow<CallState<RankingLists>> =
        MutableStateFlow(CallState.NotStarted)

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch(jobDispatcher) {
            rankingListsCallState.value = when (val response = dataSource.getRanking()) {
                is NetworkResult.Success -> {
                    CallState.Success(response.value)
                }
                is NetworkResult.Error -> CallState.Error(response.error)
            }
        }
    }

    fun onTeamClicked(team: RankedTeam) {
        val teamPage = teamPageProvider.getTeamPage(team.teamInfo)
        navigator.navigateForward(teamPage)
    }
}
