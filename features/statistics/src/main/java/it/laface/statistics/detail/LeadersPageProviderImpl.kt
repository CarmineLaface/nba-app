package it.laface.statistics.detail

import it.laface.navigation.Page
import it.laface.stats.domain.LeadersPageProvider
import it.laface.stats.domain.StatsSection

object LeadersPageProviderImpl : LeadersPageProvider {

    override fun getLeadersPage(section: StatsSection): Page {
        val fragmentClass = LeadersFragment::class.java
        val arguments = LeadersFragment.STATS_ARG_KEY to section
        return Page(
            fragmentClass = fragmentClass,
            arguments = arguments,
            tag = fragmentClass.name
        )
    }
}