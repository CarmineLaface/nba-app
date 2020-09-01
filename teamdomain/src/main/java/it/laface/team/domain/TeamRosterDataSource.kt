package it.laface.team.domain

import it.laface.base.NetworkResult
import it.laface.domain.model.Player
import it.laface.domain.model.Team

interface TeamRosterDataSource {

    suspend fun getRoster(team: Team): NetworkResult<List<Player>>
}
