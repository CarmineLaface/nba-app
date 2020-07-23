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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Suppress("EXPERIMENTAL_API_USAGE")
class TeamViewModel(
    val team: NbaTeam,
    private val rosterDataSource: TeamRosterDataSource,
    private val scheduleDataSource: ScheduleDataSource,
    private val jobDispatcher: CoroutineDispatcher
) : ViewModel() {

    val rosterCallState: MutableStateFlow<CallState<List<PlayerModel>>> =
        MutableStateFlow(CallState.InProgress)

    val scheduleCallState: MutableStateFlow<CallState<List<Game>>> =
        MutableStateFlow(CallState.InProgress)

    init {
        getRoster()
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
            when (val response = scheduleDataSource.getTeamSchedule(team)) {
                is NetworkResult.Success -> {
                    TODO()
                }
                is NetworkResult.Error -> TODO()
            }
        }
    }
}
