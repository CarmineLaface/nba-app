package it.laface.network

import it.laface.domain.datasource.TeamRepository
import it.laface.domain.model.NbaTeam

class TeamRepositoryImpl : TeamRepository {

    override suspend fun getTeamList(): List<NbaTeam> =
        Cache.teamList2

    override suspend fun getTeam(teamId: String): NbaTeam =
        getTeamList().first { team ->
            team.id == teamId
        }
}
