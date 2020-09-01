package it.laface.player.domain

import it.laface.base.NetworkResult

interface PlayerStatsDataSource {

    suspend fun getPlayerStats(playerId: String): NetworkResult<PlayerStats>
}
