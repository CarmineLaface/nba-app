package it.laface.playerdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import it.laface.common.navigation.EventType.Navigation
import it.laface.common.navigation.NavigationType
import it.laface.common.navigation.Navigator
import it.laface.common.navigation.Page
import it.laface.domain.model.PlayerModel
import it.laface.domain.navigation.PlayerDetailNavigationProvider

@Suppress("UNCHECKED_CAST")
class NavigationProvider(private val navigator: Navigator) : PlayerDetailNavigationProvider {

    override fun navigateToPlayerDetail(player: PlayerModel) {
        val fragmentClass = PlayerDetailFragment::class.java
        val arguments = Bundle(1)
        arguments.putParcelable(PlayerDetailFragment.ARGUMENT_KEY, player)
        val page = Page(
            fragmentClass = fragmentClass as Class<Fragment>,
            arguments = arguments,
            tag = fragmentClass.name
        )
        val navigation = Navigation(NavigationType.Replace(page))

        navigator.navigate(navigation)
    }
}
