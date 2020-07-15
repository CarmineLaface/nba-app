package it.laface.domain.datasource

import it.laface.domain.NetworkResult
import it.laface.domain.model.PlayerModel

interface PlayersDataSource {

    suspend fun getPlayers(): NetworkResult<List<PlayerModel>>
}
