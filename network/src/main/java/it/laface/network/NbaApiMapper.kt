package it.laface.network

import it.laface.api.NbaServices
import it.laface.domain.NetworkResult
import it.laface.domain.datasource.PlayersDataSource
import it.laface.domain.datasource.RankingDataSource
import it.laface.domain.datasource.ScheduleDataSource
import it.laface.domain.datasource.TeamRepository
import it.laface.domain.model.Game
import it.laface.domain.model.NbaTeam
import it.laface.domain.model.PlayerModel
import it.laface.domain.model.RankingLists

class NbaApiMapper(private val api: NbaServices, private val teamRepository: TeamRepository) :
    PlayersDataSource,
    ScheduleDataSource,
    RankingDataSource {

    override suspend fun getPlayers(): NetworkResult<List<PlayerModel>> {
        return api.playerList().toNetworkResult { players ->
            players.league.standard.map(::toDomain)
        }
    }

    override suspend fun getRanking(): NetworkResult<RankingLists> {
        return api.ranking().toNetworkResult { response ->
            val conference = response.league.standard.conference
            val westCoastRanking = conference.west.map(::toDomain)
            val eastCoastRanking = conference.east.map(::toDomain)
            RankingLists(
                westCoastRanking = westCoastRanking,
                eastCoastRanking = eastCoastRanking
            )
        }
    }

    override suspend fun getSchedule(): NetworkResult<List<Game>> {
        return getLeagueSchedule(teamRepository.getTeamList())
    }

    private suspend fun getLeagueSchedule(teamList: List<NbaTeam>): NetworkResult<List<Game>> =
        api.leagueSchedule().toNetworkResult { response ->
            response.league.gameList
                .filter { gameResponse ->
                    val homeTeamId = gameResponse.homeTeam.teamId
                    val visitorTeamId = gameResponse.visitorTeam.teamId
                    teamList.any { it.id == homeTeamId } && teamList.any { it.id == visitorTeamId }
                }
                .map { gameResponse ->
                    val homeTeamId = gameResponse.homeTeam.teamId
                    val visitorTeamId = gameResponse.visitorTeam.teamId
                    Game(
                        date = gameResponse.date,
                        homeTeam = teamList.first { it.id == homeTeamId },
                        visitorTeam = teamList.first { it.id == visitorTeamId }
                    )
                }
        }
}
