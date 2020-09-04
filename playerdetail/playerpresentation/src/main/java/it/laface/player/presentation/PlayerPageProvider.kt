package it.laface.player.presentation

import it.laface.navigation.Page
import it.laface.player.domain.Player
import it.laface.player.domain.PlayerDetailPageProvider

@Suppress("UNCHECKED_CAST")
object PlayerPageProvider : PlayerDetailPageProvider {

    override fun getPlayerDetailPage(player: Player): Page {
        val fragmentClass = it.laface.player.presentation.PlayerDetailFragment::class.java
        val arguments = it.laface.player.presentation.PlayerDetailFragment.ARGUMENT_KEY to player
        return Page(
            fragmentClass = fragmentClass,
            arguments = arguments,
            tag = fragmentClass.name
        )
    }
}
