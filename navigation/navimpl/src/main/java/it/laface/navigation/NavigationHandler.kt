package it.laface.navigation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import it.laface.common.ActivityProvider

class NavigationHandler(
    private val activityProvider: ActivityProvider,
    private val navHostFragmentResId: Int
) : Navigator {

    private val navController: NavController?
        get() {
            val view = activityProvider.currentActivity
                ?.findViewById<View>(navHostFragmentResId) ?: return null
            return Navigation.findNavController(view)
        }

    override fun navigateBack() {
        navController?.popBackStack()
    }

    override fun navigateForward(destination: Page) {
        navController?.navigate(destination.actionResId, destination.getBundle())
    }

    private fun Page.getBundle(): Bundle? =
        arguments?.let { bundleOf(it) }
}
