package it.laface.player.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.base.CallState
import it.laface.base.NetworkResult
import it.laface.domain.model.Team
import it.laface.navigation.Navigator
import it.laface.player.domain.Player
import it.laface.player.domain.PlayerStatsDataSource
import it.laface.team.domain.TeamPageProvider
import it.laface.team.domain.TeamRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Suppress("EXPERIMENTAL_API_USAGE")
class PlayerDetailViewModel(
    val player: Player,
    teamRepository: TeamRepository,
    private val playerStatsDataSource: PlayerStatsDataSource,
    private val jobDispatcher: CoroutineDispatcher,
    private val navigator: Navigator,
    private val teamPageProvider: TeamPageProvider
) : ViewModel() {

    val team: Team = teamRepository.getTeam(player.teamId)
    val playerStatsCallState: MutableStateFlow<CallState<List<UIPlayerStats>>> =
        MutableStateFlow(CallState.InProgress)

    init {
        getPlayerStats()
    }

    private fun getPlayerStats() {
        viewModelScope.launch(jobDispatcher) {
            val response = playerStatsDataSource.getPlayerStats(player.id)
            playerStatsCallState.value = when (response) {
                is NetworkResult.Success ->
                    CallState.Success(response.value.toUi())
                is NetworkResult.Failure -> CallState.Error(response.error)
            }
        }
    }

    fun goBack() {
        navigator.navigateBack()
    }

    fun navigateToTeamPage() {
        navigator.navigateForward(teamPageProvider.getTeamPage(team))
    }
}
