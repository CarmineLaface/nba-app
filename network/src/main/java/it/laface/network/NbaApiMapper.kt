package it.laface.network

import it.laface.api.NbaServices
import it.laface.api.models.ScheduleLeague
import it.laface.domain.NetworkResult
import it.laface.domain.datasource.PlayersDataSource
import it.laface.domain.datasource.RankingDataSource
import it.laface.domain.datasource.ScheduleDataSource
import it.laface.domain.datasource.TeamRepository
import it.laface.domain.datasource.TeamRosterDataSource
import it.laface.domain.model.Game
import it.laface.domain.model.NbaTeam
import it.laface.domain.model.PlayerModel
import it.laface.domain.model.RankingLists

class NbaApiMapper(private val api: NbaServices, private val teamRepository: TeamRepository) :
    PlayersDataSource,
    ScheduleDataSource,
    TeamRosterDataSource,
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

    override suspend fun getRoster(team: NbaTeam): NetworkResult<List<PlayerModel>> {
        return api.teamRoster(team.code).toNetworkResult { response ->
            response.roasterInfo.players.map {
                it.toDomain(team.id)
            }
        }
    }

    override suspend fun getTeamSchedule(team: NbaTeam): NetworkResult<List<Game>> {
        return api.teamSchedule(team.id).toNetworkResult { response ->
            mapSchedule(response.league, teamRepository.getTeamList())
        }
    }

    override suspend fun getLeagueSchedule(): NetworkResult<List<Game>> {
        return api.leagueSchedule().toNetworkResult { response ->
            mapSchedule(response.league, teamRepository.getTeamList())
        }
    }

    // TODO move this and fix including all games
    private fun mapSchedule(schedule: ScheduleLeague, teamList: List<NbaTeam>): List<Game> =
        schedule.standardGameList
            .filter { gameResponse ->
                val homeTeamId = gameResponse.homeTeam.teamId
                val visitorTeamId = gameResponse.visitorTeam.teamId
                teamList.any { it.id == homeTeamId } && teamList.any { it.id == visitorTeamId }
            }
            .map { gameResponse ->
                val homeTeamId = gameResponse.homeTeam.teamId
                val visitorTeamId = gameResponse.visitorTeam.teamId
                val homeScore = gameResponse.homeTeam.score?.takeIf(String::isNotEmpty)
                val visitorScore = gameResponse.visitorTeam.score?.takeIf(String::isNotEmpty)
                Game(
                    date = gameResponse.date,
                    homeTeam = teamList.first { it.id == homeTeamId },
                    visitorTeam = teamList.first { it.id == visitorTeamId },
                    homeScore = homeScore,
                    visitorScore = visitorScore
                )
            }
}
