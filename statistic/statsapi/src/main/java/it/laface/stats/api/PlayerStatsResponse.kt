package it.laface.stats.api

data class PlayerStatsResponse(
    val sections: List<StatsSectionResponse>
)

data class StatsSectionResponse(
    val players: List<PlayerResponse>,
    val title: String
)

data class PlayerResponse(
    val playerId: Int,
    val playerName: String,
    val teamId: Int,
    val value: String
)
