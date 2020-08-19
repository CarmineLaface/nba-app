package it.laface.domain.datasource

import it.laface.domain.model.PlayerModel
import it.laface.domain.network.NetworkResult

interface PlayersDataSource {

    suspend fun getPlayers(): NetworkResult<List<PlayerModel>>
}
