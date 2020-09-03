package it.laface.playerdetail

import it.laface.player.domain.PlayerStats
import it.laface.playerdetail.R.string

class UIPlayerStats(
    val nameStringResourceId: Int,
    val value: String
)

fun PlayerStats.toUi(): List<UIPlayerStats> =
    listOf(
        UIPlayerStats(string.points_per_game, pointsPerGame),
        UIPlayerStats(string.rebounds_per_game, reboundsPerGame),
        UIPlayerStats(string.assists_per_game, assistsPerGame),
    )
