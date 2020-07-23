package it.laface.domain.navigation

import it.laface.domain.model.NbaTeam

interface TeamPageProvider {

    fun getTeamPage(team: NbaTeam): Page
}
