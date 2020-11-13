package it.laface.game.domain

data class GameInfo(
    val homeTeamGame: TeamGame,
    val visitorTeamGame: TeamGame,
    val nugget: String?,
)

data class TeamGame(
    val lineScore: List<String>,
    val totalScore: String,
    val playersStats: PlayersStats,
    val teamStats: TeamStats,
)

data class TeamStats(
    val fieldGoalsMade: String,
    val fieldGoalsAttempted: String,
    val fieldGoalPercentage: String,
    val freeThrowsMade: String,
    val freeThrowsAttempted: String,
    val freeThrowsPercentage: String,
    val threePointMade: String,
    val threePointAttempted: String,
    val threePointPercentage: String,
    val offensiveRebounds: String,
    val defensiveRebounds: String,
    val totalRebounds: String,
    val assists: String,
    val steals: String,
    val turnovers: String,
    val blocks: String,
)

data class PlayersStats(
    val points: TeamLeaders,
    val rebounds: TeamLeaders,
    val assists: TeamLeaders,
)

data class TeamLeaders(
    val value: String,
    val players: List<Player>,
)

data class Player(
    val id: String,
    val firstName: String,
    val lastName: String,
)
