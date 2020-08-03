package it.laface.api.models

data class PlayerStatsResponse(
    val sections: List<StatsSection>
)

data class StatsSection(
    val players: List<Player>,
    val title: String
)

data class Player(
    val playerId: Int,
    val playerName: String,
    val teamId: Int,
    val value: String
)
