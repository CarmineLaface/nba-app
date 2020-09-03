package it.laface.ranking.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.base.CallState
import it.laface.base.NetworkResult
import it.laface.navigation.Navigator
import it.laface.ranking.domain.RankedTeam
import it.laface.ranking.domain.RankingDataSource
import it.laface.ranking.domain.RankingLists
import it.laface.team.domain.TeamPageProvider
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
            rankingListsCallState.value =
                when (val response = dataSource.getRanking()) {
                    is NetworkResult.Success ->
                        CallState.Success(response.value)
                    is NetworkResult.Error -> CallState.Error(response.error)
                }
        }
    }

    fun onTeamClicked(team: RankedTeam) {
        val teamPage = teamPageProvider.getTeamPage(team.teamInfo)
        navigator.navigateForward(teamPage)
    }
}
