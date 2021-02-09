package it.laface.team.presentation

import it.laface.navigation.Page
import it.laface.team.domain.TeamPageProvider

val teamPageProvider: TeamPageProvider
    get() = TeamPageProvider { team ->
        Page(
            fragmentClass = TeamFragment::class.java,
            arguments = TeamFragment.ARGUMENT_KEY to team,
        )
    }
