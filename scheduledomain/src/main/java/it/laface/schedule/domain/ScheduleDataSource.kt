package it.laface.schedule.domain

import it.laface.base.NetworkResult
import it.laface.domain.model.NbaTeam

interface ScheduleDataSource {

    suspend fun getLeagueSchedule(): NetworkResult<List<Game>>

    suspend fun getTeamSchedule(team: NbaTeam): NetworkResult<List<Game>>
}