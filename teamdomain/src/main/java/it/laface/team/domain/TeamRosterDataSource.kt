package it.laface.team.domain

import it.laface.base.NetworkResult
import it.laface.domain.model.NbaTeam
import it.laface.domain.model.PlayerModel

interface TeamRosterDataSource {

    suspend fun getRoster(team: NbaTeam): NetworkResult<List<PlayerModel>>
}
