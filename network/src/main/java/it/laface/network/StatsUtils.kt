package it.laface.network

import it.laface.api.models.Player
import it.laface.domain.model.Leader
import it.laface.domain.model.StatsSection

fun toDomain(index: Int, player: Player): Leader =
    Leader(
        playerName = player.playerName,
        playerId = player.playerId.toString(),
        position = index + 1,
        teamId = player.teamId.toString(),
        customValue = player.value.parseCustomValue()
    )

fun toDomain(group: it.laface.api.models.StatsSection): StatsSection =
    StatsSection(
        title = group.title,
        players = group.players.mapIndexed(::toDomain)
    )

@Suppress("MagicNumber")
fun String.parseCustomValue(): String {
    if (contains('.').not()) return this
    val pointIndex = indexOf('.')
    return if (startsWith("0.")) {
        substring(1, pointIndex + 4)
    } else {
        substring(0, pointIndex + 3)
    }
}
