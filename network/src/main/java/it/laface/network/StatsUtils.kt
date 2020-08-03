package it.laface.network

import it.laface.api.models.Player
import it.laface.api.models.StatsSection
import it.laface.domain.model.Leader
import it.laface.domain.model.StatsGroup

fun toDomain(index: Int, player: Player) = Leader(
    playerName = player.playerName,
    playerId = player.playerId.toString(),
    position = index,
    teamId = player.teamId.toString()
)

fun toDomain(group: StatsSection) = StatsGroup(
    title = group.title,
    players = group.players.mapIndexed(::toDomain)
)
