package it.laface.network

import it.laface.api.NbaServices
import it.laface.domain.NetworkResult
import it.laface.domain.datasource.TeamRepository
import it.laface.domain.mapSuccess
import it.laface.domain.model.RankedTeam

class TeamRepositoryImpl(private val api: NbaServices): TeamRepository {

    override suspend fun save(teamList: List<RankedTeam>) {
        Cache.teamList = teamList
    }

    override suspend fun getTeamList(): NetworkResult<List<RankedTeam>> =
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

    override suspend fun getTeam(teamId: String): NetworkResult<RankedTeam> =
        getTeamList().mapSuccess { teamList ->
            teamList.first { team ->
                team.id == teamId
            }
        }
}
