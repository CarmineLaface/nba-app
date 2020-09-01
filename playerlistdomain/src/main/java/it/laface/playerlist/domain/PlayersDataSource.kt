package it.laface.playerlist.domain

import it.laface.base.NetworkResult
import it.laface.domain.model.Player

interface PlayersDataSource {

    suspend fun getPlayers(): NetworkResult<List<Player>>
}
