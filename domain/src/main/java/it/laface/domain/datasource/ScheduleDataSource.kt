package it.laface.domain.datasource

import it.laface.domain.NetworkResult
import it.laface.domain.model.Game
import it.laface.domain.model.NbaTeam

interface ScheduleDataSource {

    suspend fun getLeagueSchedule(): NetworkResult<List<Game>>

    suspend fun getTeamSchedule(team: NbaTeam): NetworkResult<List<Game>>
}
