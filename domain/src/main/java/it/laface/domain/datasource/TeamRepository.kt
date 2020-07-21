package it.laface.domain.datasource

import it.laface.domain.model.NbaTeam

interface TeamRepository {

    suspend fun getTeamList(): List<NbaTeam>
    suspend fun getTeam(teamId: String): NbaTeam
}
