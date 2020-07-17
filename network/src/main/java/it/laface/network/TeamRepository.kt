package it.laface.network

import it.laface.api.NbaServices
import it.laface.domain.NetworkResult
import it.laface.domain.model.RankedTeam

class TeamRepository(private val api: NbaServices) {

    fun save(teamList: List<RankedTeam>) {
        Cache.teamList = teamList
    }

    suspend fun get(): NetworkResult<List<RankedTeam>> =
        Cache.teamList?.let {
            NetworkResult.Success(it)
        } ?: api.ranking().toNetworkResult { response ->
            val conference = response.league.standard.conference
            val westCoastRanking = conference.west.map(::toDomain)
            val eastCoastRanking = conference.east.map(::toDomain)
            val completeList = westCoastRanking + eastCoastRanking
            save(completeList)
            return@toNetworkResult completeList
        }
}
