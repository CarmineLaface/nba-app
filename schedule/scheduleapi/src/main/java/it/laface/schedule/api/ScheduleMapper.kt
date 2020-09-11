package it.laface.schedule.api

import it.laface.base.NetworkResult
import it.laface.domain.model.Team
import it.laface.networking.toNetworkResult
import it.laface.schedule.domain.Game
import it.laface.schedule.domain.ScheduleDataSource
import it.laface.team.domain.TeamRepository

class ScheduleMapper(
    private val service: ScheduleService,
    private val teamRepository: TeamRepository
) : ScheduleDataSource {

    override suspend fun getTeamSchedule(teamId: String): NetworkResult<List<Game>> {
        return service.teamSchedule(teamId).toNetworkResult { response ->
            mapSchedule(response.league, teamRepository.getTeamList())
        }
    }

    override suspend fun getLeagueSchedule(): NetworkResult<List<Game>> {
        return service.leagueSchedule().toNetworkResult { response ->
            mapSchedule(response.league, teamRepository.getTeamList())
        }
    }

    private fun mapSchedule(schedule: ScheduleLeague, teamList: List<Team>): List<Game> =
        schedule.getAllGames()
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
                    id = gameResponse.gameId,
                    date = gameResponse.date,
                    homeTeam = teamList.first { it.id == homeTeamId },
                    visitorTeam = teamList.first { it.id == visitorTeamId },
                    homeScore = homeScore,
                    visitorScore = visitorScore
                )
            }

    private fun ScheduleLeague.getAllGames(): List<GameResponse> =
        standardGameList + sacramentoGameList + vegasGameList + utahGameList
}
