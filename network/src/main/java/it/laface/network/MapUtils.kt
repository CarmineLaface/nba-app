package it.laface.network

import it.laface.api.models.NbaPlayer
import it.laface.api.models.NbaTeam
import it.laface.domain.model.PlayerModel
import it.laface.domain.model.RankedTeam

fun toDomain(team: NbaTeam) = RankedTeam(
    rankingPosition = team.rankingPosition.toIntString,
    id = team.id,
    code = team.info.tricode,
    name = "${team.info.name} ${team.info.nickname}"
)

fun toDomain(nbaPlayer: NbaPlayer) = PlayerModel(
    name = nbaPlayer.firstName,
    surname = nbaPlayer.lastName,
    id = nbaPlayer.personId,
    teamId = nbaPlayer.teamId,
    jerseyNumber = nbaPlayer.jerseyNumber,
    position = nbaPlayer.position
)

val String.toIntString: String
    get() = if (length == 1) "0$this" else this
