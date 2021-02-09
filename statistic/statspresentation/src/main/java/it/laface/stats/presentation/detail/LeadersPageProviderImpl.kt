package it.laface.stats.presentation.detail

import it.laface.navigation.Page
import it.laface.stats.domain.LeadersPageProvider

val leadersPageProvider: LeadersPageProvider
    get() = LeadersPageProvider { section ->
        Page(
            fragmentClass = LeadersFragment::class.java,
            arguments = LeadersFragment.STATS_ARG_KEY to section,
        )
    }
