package it.laface.game.networking

import it.laface.base.NetworkResult
import it.laface.game.domain.GameDataSource
import it.laface.game.domain.GameInfo
import it.laface.game.domain.Player
import it.laface.game.domain.PlayersStats
import it.laface.game.domain.TeamGame
import it.laface.game.domain.TeamLeaders
import it.laface.game.domain.TeamStats
import it.laface.networking.toNetworkResult

class GameMapper(private val service: GameService) : GameDataSource {

    override suspend fun getInfo(gameDateFormatted: String, id: String): NetworkResult<GameInfo> {
        return service.gameBoxScore(gameDateFormatted, id).toNetworkResult { response ->
            val homeGameStats = response.gameStats.homeTeam
            val homeGameData = response.basicGameData.homeTeam
            val visitorGameStats = response.gameStats.visitorTeam
            val visitorGameData = response.basicGameData.visitorTeam
            GameInfo(
                homeTeamGame = toDomain(homeGameStats, homeGameData),
                visitorTeamGame = toDomain(visitorGameStats, visitorGameData),
                nugget = response.basicGameData.nugget?.text
            )
        }
    }
}

private fun toDomain(gameStats: TeamStatsResponse, gameData: TeamGameData): TeamGame =
    TeamGame(
        lineScore = gameData.linescore.map(Score::score),
        totalScore = gameData.totalScore,
        playersStats = gameStats.leaders.toDomain(),
        teamStats = gameStats.totals.toDomain(),
    )

private fun toPlayerDomain(player: PlayerResponse): DomainPlayer =
    DomainPlayer(
        id = player.id,
        firstName = player.firstName,
        lastName = player.lastName,
    )

private fun TeamLeadersResponse.toDomain(): DomainTeamLeaders =
    DomainTeamLeaders(
        value = value,
        players = players.map(::toPlayerDomain)
    )

private fun Leaders.toDomain(): PlayersStats =
    PlayersStats(
        points = points.toDomain(),
        rebounds = rebounds.toDomain(),
        assists = assists.toDomain()
    )

private fun TotalStats.toDomain(): DomainTeamStats =
    DomainTeamStats(
        fieldGoalsMade = fieldGoalsMade,
        fieldGoalsAttempted = fieldGoalsAttempted,
        fieldGoalPercentage = fieldGoalPercentage,
        freeThrowsMade = freeThrowsMade,
        freeThrowsAttempted = freeThrowsAttempted,
        freeThrowsPercentage = freeThrowsPercentage,
        threePointMade = threePointMade,
        threePointAttempted = threePointAttempted,
        threePointPercentage = threePointPercentage,
        offensiveRebounds = offensiveRebounds,
        defensiveRebounds = defensiveRebounds,
        totalRebounds = totalRebounds,
        assists = assists,
        steals = steals,
        turnovers = turnovers,
        blocks = blocks,
    )

typealias DomainTeamStats = TeamStats
typealias DomainPlayer = Player
typealias DomainTeamLeaders = TeamLeaders
