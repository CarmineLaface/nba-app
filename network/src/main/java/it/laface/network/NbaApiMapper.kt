package it.laface.network

import it.laface.api.NbaServices
import it.laface.api.models.NbaTeam
import it.laface.domain.NetworkResult
import it.laface.domain.PlayerModel
import it.laface.domain.PlayersDataSource
import it.laface.domain.RankedTeam
import it.laface.domain.RankingDataSource
import it.laface.domain.RankingLists

class NbaApiMapper(private val api: NbaServices) :
    PlayersDataSource,
    RankingDataSource {

    override suspend fun getPlayers(): NetworkResult<List<PlayerModel>> {
        return api.playerList().toNetworkResult { players ->
            players.league.standard.map { nbaPlayer ->
                PlayerModel(
                    name = nbaPlayer.firstName,
                    surname = nbaPlayer.lastName,
                    id = nbaPlayer.personId,
                    teamId = nbaPlayer.teamId,
                    jerseyNumber = nbaPlayer.jerseyNumber,
                    position = nbaPlayer.position
                )
            }
        }
    }

    override suspend fun getRanking(): NetworkResult<RankingLists> {
        fun toDomain(team: NbaTeam) = RankedTeam(
            rankingPosition = team.rankingPosition,
            id = team.id,
            code = team.info.tricode,
            name = "${team.info.name} ${team.info.nickname}"
        )
        return api.ranking().toNetworkResult { response ->
            val conference = response.league.standard.conference
            RankingLists(
                westCoastRanking = conference.west.map(::toDomain),
                eastCoastRanking = conference.east.map(::toDomain)
            )
        }
    }
}
