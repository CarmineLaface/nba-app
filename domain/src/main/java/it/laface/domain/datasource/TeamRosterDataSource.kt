package it.laface.domain.datasource

import it.laface.domain.NetworkResult
import it.laface.domain.model.NbaTeam
import it.laface.domain.model.PlayerModel

interface TeamRosterDataSource {

    suspend fun getRoster(team: NbaTeam): NetworkResult<List<PlayerModel>>
}
