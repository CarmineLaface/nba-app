package it.laface.stats.presentation.group

import it.laface.navigation.Page
import it.laface.stats.domain.StatsPageProvider

val statsPageProvider: StatsPageProvider
    get() = StatsPageProvider {
        Page(
            fragmentClass = StatsFragment::class.java,
        )
    }
