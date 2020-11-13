package it.laface.game.domain

import it.laface.base.NetworkResult

interface GameDataSource {

    suspend fun getInfo(gameDateFormatted: String, id: String): NetworkResult<GameInfo>
}
