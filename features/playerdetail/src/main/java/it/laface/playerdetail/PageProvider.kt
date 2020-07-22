package it.laface.playerdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import it.laface.domain.model.PlayerModel
import it.laface.domain.navigation.Page
import it.laface.domain.navigation.PlayerDetailPageProvider

@Suppress("UNCHECKED_CAST")
object PageProvider : PlayerDetailPageProvider {

    override fun getPlayerDetailPage(player: PlayerModel): Page {
        val fragmentClass = PlayerDetailFragment::class.java
        val arguments = Bundle(1)
        arguments.putParcelable(PlayerDetailFragment.ARGUMENT_KEY, player)
        return Page(
            fragmentClass = fragmentClass as Class<Fragment>,
            arguments = arguments,
            tag = fragmentClass.name
        )
    }
}
