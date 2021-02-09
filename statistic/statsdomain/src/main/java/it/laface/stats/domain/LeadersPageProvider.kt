package it.laface.stats.domain

import it.laface.navigation.Page

fun interface LeadersPageProvider {

    fun getLeadersPage(section: StatsSection): Page
}
