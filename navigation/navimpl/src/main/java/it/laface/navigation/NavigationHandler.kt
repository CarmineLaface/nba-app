package it.laface.navigation

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.findNavController

class NavigationHandler(
    activity: Activity,
    navHostFragmentResId: Int
) : Navigator {

    private val navController: NavController =
        activity.findNavController(navHostFragmentResId)

    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun navigateForward(destination: Page) {
        navController.navigate(destination.actionResId, destination.getBundle())
    }
}
