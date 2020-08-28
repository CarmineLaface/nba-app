package it.laface.playerdetail

import android.os.Bundle
import it.laface.domain.model.PlayerModel
import it.laface.domain.navigation.Page
import it.laface.player.domain.PlayerDetailPageProvider

@Suppress("UNCHECKED_CAST")
object PlayerPageProvider : it.laface.player.domain.PlayerDetailPageProvider {

    override fun getPlayerDetailPage(player: PlayerModel): Page {
        val fragmentClass = PlayerDetailFragment::class.java
        val arguments = Bundle(1)
        arguments.putParcelable(PlayerDetailFragment.ARGUMENT_KEY, player)
        return Page(
            fragmentClass = fragmentClass,
            arguments = arguments,
            tag = fragmentClass.name
        )
    }
}
