package it.laface.domain.datasource

import it.laface.domain.NetworkResult
import it.laface.domain.model.RankedTeam

interface TeamRepository {

    suspend fun save(teamList: List<RankedTeam>)
    suspend fun getTeamList(): NetworkResult<List<RankedTeam>>
    suspend fun getTeam(teamId: String): NetworkResult<RankedTeam>
}