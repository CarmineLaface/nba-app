package it.laface.stats.domain

import it.laface.navigation.Page

fun interface StatsPageProvider {

    fun getStatsPage(): Page
}
