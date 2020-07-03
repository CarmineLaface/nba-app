package it.laface.domain

interface PlayersDataSource {

    suspend fun getPlayers(): NetworkResult<List<PlayerModel>>
}
