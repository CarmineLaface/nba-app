package it.laface.team.api

import it.laface.base.NetworkResult
import it.laface.domain.model.Player
import it.laface.domain.model.Team
import it.laface.networking.toNetworkResult
import it.laface.team.domain.TeamRosterDataSource

class TeamRosterMapper(private val service: TeamRosterService) : TeamRosterDataSource {

    override suspend fun getRoster(team: Team): NetworkResult<List<Player>> {
        val teamSlug = when(team.code) {
            "blazers" -> "trail_blazers"
            "sixers" -> "76ers"
            else -> team.code
        }
        return service.teamRoster(teamSlug).toNetworkResult { response ->
            response.roasterInfo.players.map { player ->
                Player(
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
}
