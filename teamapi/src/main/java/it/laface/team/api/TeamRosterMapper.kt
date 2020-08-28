package it.laface.team.api

import it.laface.base.NetworkResult
import it.laface.domain.model.NbaTeam
import it.laface.domain.model.PlayerModel
import it.laface.networking.toNetworkResult
import it.laface.team.domain.TeamRosterDataSource

class TeamRosterMapper(private val service: TeamRosterService) : TeamRosterDataSource {

    override suspend fun getRoster(team: NbaTeam): NetworkResult<List<PlayerModel>> =
        service.teamRoster(team.code).toNetworkResult { response ->
            response.roasterInfo.players.map { player ->
                PlayerModel(
                    name = player.name,
                    surname = player.surname,
                    id = player.id.toString(),
                    teamId = team.id,
                    jerseyNumber = player.jerseyNumber,
                    position = player.position
                )
            }
        }
}
