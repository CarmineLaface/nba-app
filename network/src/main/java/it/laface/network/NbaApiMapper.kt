package it.laface.network

import it.laface.api.NbaServices
import it.laface.api.models.NbaTeam
import it.laface.domain.NetworkResult
import it.laface.domain.datasource.PlayersDataSource
import it.laface.domain.datasource.RankingDataSource
import it.laface.domain.datasource.ScheduleDataSource
import it.laface.domain.model.Game
import it.laface.domain.model.PlayerModel
import it.laface.domain.model.RankedTeam
import it.laface.domain.model.RankingLists
import it.laface.domain.model.Team

class NbaApiMapper(private val api: NbaServices) :
    PlayersDataSource,
    ScheduleDataSource,
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
        return api.ranking().toNetworkResult { response ->
            val conference = response.league.standard.conference
            RankingLists(
                westCoastRanking = conference.west.map(::toDomain),
                eastCoastRanking = conference.east.map(::toDomain)
            )
        }
    }

    override suspend fun getSchedule(): NetworkResult<List<Game>> {
        return api.leagueSchedule().toNetworkResult { response ->
            response.league.gameList
                .map { gameResponse ->
                    Game(
                        date = gameResponse.date,
                        homeTeam = Team(gameResponse.homeTeam.teamId, "", ""),
                        visitorTeam = Team(gameResponse.visitorTeam.teamId, "", "")
                    )
                }
        }
    }
}
