package it.laface.statistics

import androidx.fragment.app.Fragment
import it.laface.domain.navigation.Page
import it.laface.domain.navigation.StatsPageProvider

object StatsPageProviderImpl : StatsPageProvider {

    override fun getStatsPage(): Page {
        val fragmentClass = StatsFragment::class.java
        return Page(
            fragmentClass = fragmentClass as Class<Fragment>,
            arguments = null,
            tag = fragmentClass.name
        )
    }
}
