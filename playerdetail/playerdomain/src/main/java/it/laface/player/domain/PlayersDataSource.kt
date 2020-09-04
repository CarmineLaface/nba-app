package it.laface.player.domain

import it.laface.base.NetworkResult

interface PlayersDataSource {

    suspend fun getPlayers(): NetworkResult<List<Player>>
}
