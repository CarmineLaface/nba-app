package it.laface.team.domain

import it.laface.domain.model.NbaTeam
import it.laface.navigation.Page

interface TeamPageProvider {

    fun getTeamPage(team: NbaTeam): Page
}
