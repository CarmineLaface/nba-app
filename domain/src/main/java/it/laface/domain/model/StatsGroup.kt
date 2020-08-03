package it.laface.domain.model

data class StatsGroup(
    val players: List<Leader>,
    val title: String
)

data class Leader(
    val playerId: String,
    val playerName: String,
    val position: Int,
    val teamId: String
)
