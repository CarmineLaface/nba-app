package it.laface.team.domain

import it.laface.domain.model.Team
import it.laface.navigation.Page

interface TeamPageProvider {

    fun getTeamPage(team: Team): Page
}
