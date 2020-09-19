package it.laface.team.presentation

import it.laface.base.CallState
import it.laface.base.NetworkResult
import it.laface.domain.model.Team
import it.laface.player.domain.Player
import it.laface.player.domain.TeamRosterDataSource
import it.laface.schedule.domain.Game
import it.laface.schedule.domain.ScheduleDataSource
import it.laface.team.domain.TeamInfo
import it.laface.team.domain.TeamInfoDataSource
import kotlinx.coroutines.flow.MutableStateFlow

@Suppress("EXPERIMENTAL_API_USAGE")
class TeamDataSourcesManager(
    private val rosterDataSource: TeamRosterDataSource,
    private val scheduleDataSource: ScheduleDataSource,
    private val teamInfoDataSource: TeamInfoDataSource,
) {
    val rosterCallState: MutableStateFlow<CallState<List<Player>>> =
        MutableStateFlow(CallState.NotStarted)
    val scheduleCallState: MutableStateFlow<CallState<List<Game>>> =
        MutableStateFlow(CallState.NotStarted)
    val teamInfoCallState: MutableStateFlow<CallState<TeamInfo>> =
        MutableStateFlow(CallState.NotStarted)

    suspend fun loadRoster(team: Team) {
        rosterCallState.value = CallState.InProgress
        val response = rosterDataSource.getRoster(teamCode = team.code, teamId = team.id)
        rosterCallState.value = when (response) {
            is NetworkResult.Success ->
                CallState.Success(response.value)
            is NetworkResult.Error ->
                CallState.Error(response.error)
        }
    }

    suspend fun loadSchedule(team: Team) {
        rosterCallState.value = CallState.InProgress
        val response = scheduleDataSource.getTeamSchedule(team.id)
        scheduleCallState.value = when (response) {
            is NetworkResult.Success ->
                CallState.Success(response.value)
            is NetworkResult.Error ->
                CallState.Error(response.error)
        }
    }

    suspend fun loadInfo(team: Team) {
        rosterCallState.value = CallState.InProgress
        val response = teamInfoDataSource.getTeamInfo(team.id)
        teamInfoCallState.value = when (response) {
            is NetworkResult.Success ->
                CallState.Success(response.value)
            is NetworkResult.Error ->
                CallState.Error(response.error)
        }
    }
}
