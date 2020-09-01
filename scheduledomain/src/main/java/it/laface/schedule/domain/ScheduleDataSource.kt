package it.laface.schedule.domain

import it.laface.base.NetworkResult

interface ScheduleDataSource {

    suspend fun getLeagueSchedule(): NetworkResult<List<Game>>

    suspend fun getTeamSchedule(teamId: String): NetworkResult<List<Game>>
}
