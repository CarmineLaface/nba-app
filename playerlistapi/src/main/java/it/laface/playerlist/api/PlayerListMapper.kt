package it.laface.playerlist.api

import it.laface.base.NetworkResult
import it.laface.domain.model.PlayerModel
import it.laface.networking.toNetworkResult
import it.laface.playerlist.domain.PlayersDataSource

class PlayerListMapper(private val service: PlayerListService) : PlayersDataSource {

    override suspend fun getPlayers(): NetworkResult<List<PlayerModel>> {
        return service.playerList().toNetworkResult { players ->
            players.league.standard.map { nbaPlayer ->
                PlayerModel(
                    name = nbaPlayer.firstName,
                    surname = nbaPlayer.lastName,
                    id = nbaPlayer.personId,
                    teamId = nbaPlayer.teamId,
                    jerseyNumber = nbaPlayer.jerseyNumber,
                    position = nbaPlayer.position
                )
            }
        }
    }
}
