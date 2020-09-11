package it.laface.team.api.roster

import it.laface.base.NetworkResult
import it.laface.networking.toNetworkResult
import it.laface.player.domain.Player
import it.laface.player.domain.TeamRosterDataSource

class TeamRosterMapper(private val service: TeamRosterService) :
    TeamRosterDataSource {

    override suspend fun getRoster(teamCode: String, teamId: String): NetworkResult<List<Player>> {
        val teamSlug = when (teamCode) {
            "blazers" -> "trail_blazers"
            "sixers" -> "76ers"
            else -> teamCode
        }
        return service.teamRoster(teamSlug).toNetworkResult { response ->
            response.roasterInfo.players.map { player ->
                Player(
                    name = player.name,
                    surname = player.surname,
                    id = player.id.toString(),
                    teamId = teamId,
                    jerseyNumber = player.jerseyNumber,
                    position = player.position
                )
            }
        }
    }
}
