package it.laface.player.domain

import it.laface.base.NetworkResult

interface TeamRosterDataSource {

    suspend fun getRoster(teamCode: String, teamId: String): NetworkResult<List<Player>>
}
