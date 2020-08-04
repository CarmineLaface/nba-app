package it.laface.statistics.detail

import android.os.Bundle
import it.laface.domain.model.StatsSection
import it.laface.domain.navigation.Page

interface LeadersPageProvider {

    fun getLeadersPage(section: StatsSection): Page
}

object LeadersPageProviderImpl : LeadersPageProvider {

    override fun getLeadersPage(section: StatsSection): Page {
        val fragmentClass = LeadersFragment::class.java
        val arguments = Bundle(1)
        arguments.putParcelable(LeadersFragment.STATS_ARG_KEY, section)
        return Page(
            fragmentClass = fragmentClass,
            arguments = arguments,
            tag = fragmentClass.name
        )
    }
}
