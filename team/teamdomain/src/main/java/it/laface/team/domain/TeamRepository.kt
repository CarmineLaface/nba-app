package it.laface.team.domain

import it.laface.domain.model.Team

interface TeamRepository {

    fun getTeamList(): List<Team>
    fun getTeam(teamId: String): Team
}
