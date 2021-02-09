package it.laface.player.presentation

import it.laface.navigation.Page
import it.laface.player.domain.PlayerDetailPageProvider

val playerPageProvider: PlayerDetailPageProvider
    get() = PlayerDetailPageProvider { player ->
        Page(
            fragmentClass = PlayerDetailFragment::class.java,
            arguments = PlayerDetailFragment.ARGUMENT_KEY to player,
        )
    }
