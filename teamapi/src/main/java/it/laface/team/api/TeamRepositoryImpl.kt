package it.laface.team.api

import it.laface.domain.model.Team
import it.laface.team.domain.TeamRepository

class TeamRepositoryImpl : TeamRepository {

    override fun getTeamList(): List<Team> =
        Const.teams

    override fun getTeam(teamId: String): Team =
        getTeamList().first { team ->
            team.id == teamId
        }
}
