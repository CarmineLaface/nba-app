package it.laface.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.domain.CallState
import it.laface.domain.NetworkResult
import it.laface.domain.datasource.ScheduleDataSource
import it.laface.domain.datasource.TeamRosterDataSource
import it.laface.domain.model.Game
import it.laface.domain.model.NbaTeam
import it.laface.domain.model.PlayerModel
import it.laface.domain.navigation.Navigator
import it.laface.domain.navigation.PlayerDetailPageProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Suppress("EXPERIMENTAL_API_USAGE")
class TeamViewModel(
    val team: NbaTeam,
    private val rosterDataSource: TeamRosterDataSource,
    private val scheduleDataSource: ScheduleDataSource,
    private val jobDispatcher: CoroutineDispatcher,
    private val navigator: Navigator,
    private val playerPageProvider: PlayerDetailPageProvider
) : ViewModel() {

    val rosterCallState: MutableStateFlow<CallState<List<PlayerModel>>> =
        MutableStateFlow(CallState.InProgress)

    val scheduleCallState: MutableStateFlow<CallState<List<Game>>> =
        MutableStateFlow(CallState.InProgress)

    init {
        getRoster()
        getSchedule()
    }

    private fun getRoster() {
        viewModelScope.launch(jobDispatcher) {
            rosterCallState.value = when (val response = rosterDataSource.getRoster(team)) {
                is NetworkResult.Success -> {
                    CallState.Success(response.value)
                }
                is NetworkResult.Error ->
                    CallState.Error(response.error)
            }
        }
    }

    private fun getSchedule() {
        viewModelScope.launch(jobDispatcher) {
            scheduleCallState.value = when (val response = scheduleDataSource.getTeamSchedule(team)) {
                is NetworkResult.Success -> {
                    CallState.Success(response.value)
                }
                is NetworkResult.Error ->
                    CallState.Error(response.error)
            }
        }
    }

    fun playerSelected(player: PlayerModel) {
        navigator.navigateForward(playerPageProvider.getPlayerDetailPage(player))
    }
}
