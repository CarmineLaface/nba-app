package it.laface.team

import it.laface.domain.model.Team
import it.laface.navigation.Page
import it.laface.team.domain.TeamPageProvider

object TeamPageProviderImpl : TeamPageProvider {

    override fun getTeamPage(team: Team): Page {
        val fragmentClass = TeamFragment::class.java
        val arguments = TeamFragment.ARGUMENT_KEY to team
        return Page(
            fragmentClass = fragmentClass,
            arguments = arguments,
            tag = fragmentClass.name
        )
    }
}
