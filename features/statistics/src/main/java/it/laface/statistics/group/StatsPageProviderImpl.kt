package it.laface.statistics.group

import it.laface.navigation.Page
import it.laface.stats.domain.StatsPageProvider

object StatsPageProviderImpl : StatsPageProvider {

    override fun getStatsPage(): Page {
        val fragmentClass = StatsFragment::class.java
        return Page(
            fragmentClass = fragmentClass,
            arguments = null,
            tag = fragmentClass.name
        )
    }
}
