package it.laface.network

import it.laface.api.models.NbaPlayer
import it.laface.api.models.TeamResponseModel
import it.laface.domain.model.NbaTeam
import it.laface.domain.model.PlayerModel
import it.laface.domain.model.RankedTeam

fun toDomain(team: TeamResponseModel) = RankedTeam(
    rankingPosition = team.rankingPosition.toIntString,
    teamInfo = NbaTeam(
        id = team.id,
        key = team.info.key,
        code = team.info.code,
        name = team.info.name,
        tricode = team.info.tricode,
        nickname = team.info.nickname
    )
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
