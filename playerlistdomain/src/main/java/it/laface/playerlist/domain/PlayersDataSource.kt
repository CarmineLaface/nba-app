package it.laface.playerlist.domain

import it.laface.base.NetworkResult
import it.laface.domain.model.PlayerModel

interface PlayersDataSource {

    suspend fun getPlayers(): NetworkResult<List<PlayerModel>>
}
