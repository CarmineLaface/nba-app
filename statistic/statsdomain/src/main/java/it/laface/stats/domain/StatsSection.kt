package it.laface.stats.domain

import java.io.Serializable

data class StatsSection(
    val players: List<Leader>,
    val title: String
) : Serializable

data class Leader(
    val playerId: String,
    val playerName: String,
    val rank: Int,
    val teamId: String,
    val customValue: String
) : Serializable
