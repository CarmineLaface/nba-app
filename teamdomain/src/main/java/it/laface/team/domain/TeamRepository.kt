package it.laface.team.domain

import it.laface.domain.model.NbaTeam

interface TeamRepository {

    suspend fun getTeamList(): List<NbaTeam>
    suspend fun getTeam(teamId: String): NbaTeam
}
