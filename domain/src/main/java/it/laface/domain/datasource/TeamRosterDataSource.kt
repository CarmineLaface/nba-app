package it.laface.domain.datasource

import it.laface.domain.model.NbaTeam
import it.laface.domain.model.PlayerModel
import it.laface.domain.network.NetworkResult

interface TeamRosterDataSource {

    suspend fun getRoster(team: NbaTeam): NetworkResult<List<PlayerModel>>
}
