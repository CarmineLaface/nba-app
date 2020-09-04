package it.laface.player.presentation

import it.laface.player.domain.PlayerStats

class UIPlayerStats(
    val nameStringResourceId: Int,
    val value: String
)

fun PlayerStats.toUi(): List<UIPlayerStats> =
    listOf(
        UIPlayerStats(R.string.points_per_game, pointsPerGame),
        UIPlayerStats(R.string.rebounds_per_game, reboundsPerGame),
        UIPlayerStats(R.string.assists_per_game, assistsPerGame),
    )
