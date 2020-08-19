package it.laface.domain.datasource

import it.laface.domain.model.Game
import it.laface.domain.model.NbaTeam
import it.laface.domain.network.NetworkResult

interface ScheduleDataSource {

    suspend fun getLeagueSchedule(): NetworkResult<List<Game>>

    suspend fun getTeamSchedule(team: NbaTeam): NetworkResult<List<Game>>
}
