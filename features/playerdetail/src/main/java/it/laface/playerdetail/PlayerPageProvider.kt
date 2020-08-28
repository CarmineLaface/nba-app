package it.laface.playerdetail

import it.laface.domain.model.PlayerModel
import it.laface.navigation.Page
import it.laface.player.domain.PlayerDetailPageProvider

@Suppress("UNCHECKED_CAST")
object PlayerPageProvider : PlayerDetailPageProvider {

    override fun getPlayerDetailPage(player: PlayerModel): Page {
        val fragmentClass = PlayerDetailFragment::class.java
        val arguments = PlayerDetailFragment.ARGUMENT_KEY to player
        return Page(
            fragmentClass = fragmentClass,
            arguments = arguments,
            tag = fragmentClass.name
        )
    }
}
