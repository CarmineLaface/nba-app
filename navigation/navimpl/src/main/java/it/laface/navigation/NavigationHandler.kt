package it.laface.navigation

import androidx.navigation.NavController

class NavigationHandler(
    private val navController: NavController
) : Navigator {

    override fun navigate(navigation: NavigationInfo) {
        when (navigation) {
            is NavigationInfo.Forward -> navigateForward(navigation)
            NavigationInfo.Back -> navController.popBackStack()
        }
    }

    private fun navigateForward(navigation: NavigationInfo.Forward) {
        val destination = navigation.destination
        navController.navigate(destination.actionResId, destination.getBundle())
    }
}
