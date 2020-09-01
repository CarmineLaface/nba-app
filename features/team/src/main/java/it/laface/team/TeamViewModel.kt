package it.laface.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.base.CallState
import it.laface.base.NetworkResult
import it.laface.common.ContentToShow
import it.laface.domain.model.Player
import it.laface.domain.model.Team
import it.laface.navigation.Navigator
import it.laface.player.domain.PlayerDetailPageProvider
import it.laface.schedule.domain.Game
import it.laface.schedule.domain.ScheduleDataSource
import it.laface.team.domain.TeamRosterDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Date

@Suppress("EXPERIMENTAL_API_USAGE")
class TeamViewModel(
    val team: Team,
    private val rosterDataSource: TeamRosterDataSource,
    private val scheduleDataSource: ScheduleDataSource,
    private val jobDispatcher: CoroutineDispatcher,
    private val navigator: Navigator,
    private val playerPageProvider: PlayerDetailPageProvider
) : ViewModel() {

    private val rosterCallState: MutableStateFlow<CallState<List<Player>>> =
        MutableStateFlow(CallState.InProgress)

    val rosterContent: Flow<ContentToShow<Player>> = rosterCallState.map { callState ->
        when (callState) {
            is CallState.Success -> ContentToShow.Success(callState.result)
            is CallState.Error -> ContentToShow.Error
            CallState.NotStarted, CallState.InProgress -> ContentToShow.Loading
        }
    }

    private val scheduleCallState: MutableStateFlow<CallState<List<Game>>> =
        MutableStateFlow(CallState.InProgress)

    val scheduleContent: Flow<ContentToShow<Game>> = scheduleCallState.map { callState ->
        when (callState) {
            is CallState.Success -> ContentToShow.Success(callState.result)
            is CallState.Error -> ContentToShow.Error
            CallState.NotStarted, CallState.InProgress -> ContentToShow.Loading
        }
    }

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
            scheduleCallState.value =
                when (val response = scheduleDataSource.getTeamSchedule(team.id)) {
                    is NetworkResult.Success -> {
                        CallState.Success(response.value)
                    }
                    is NetworkResult.Error ->
                        CallState.Error(response.error)
                }
        }
    }

    fun playerSelected(player: Player) {
        navigator.navigateForward(playerPageProvider.getPlayerDetailPage(player))
    }

    fun scrollScheduleToIndex(schedule: List<Game>): Int {
        val today = Date()
        return schedule.indexOfFirst { it.date.after(today) }
    }

    fun navigateBack() {
        navigator.navigateBack()
    }
}
