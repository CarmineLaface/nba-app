package it.laface.team.domain

import it.laface.base.NetworkResult

interface TeamInfoDataSource {

    suspend fun getTeamInfo(teamId: String): NetworkResult<TeamInfo>
}
