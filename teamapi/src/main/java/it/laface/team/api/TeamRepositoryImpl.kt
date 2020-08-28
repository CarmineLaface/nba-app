package it.laface.team.api

import it.laface.domain.model.NbaTeam
import it.laface.team.domain.TeamRepository

class TeamRepositoryImpl : TeamRepository {

    override suspend fun getTeamList(): List<NbaTeam> =
        Cache.teamList2

    override suspend fun getTeam(teamId: String): NbaTeam =
        getTeamList().first { team ->
            team.id == teamId
        }
}