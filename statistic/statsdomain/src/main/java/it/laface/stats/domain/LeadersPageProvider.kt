package it.laface.stats.domain

import it.laface.navigation.Page

interface LeadersPageProvider {

    fun getLeadersPage(section: StatsSection): Page
}
