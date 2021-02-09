package it.laface.navigation

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import it.laface.common.ActivityProvider

@Suppress("UNCHECKED_CAST")
class NavigationHandler(
    private val activityProvider: ActivityProvider,
    private val containerViewResId: Int
) : Navigator {

    private val manager: FragmentManager?
        get() = (activityProvider.currentActivity as? FragmentActivity)?.supportFragmentManager

    override fun navigateBack() {
        manager?.popBackStack()
    }

    override fun navigateForward(destination: Page) {
        val fragmentClass = destination.fragmentClass as? Class<out Fragment> ?: return
        manager?.commit {
            replace(containerViewResId, fragmentClass, destination.getBundle(), fragmentClass.name)
            addToBackStack(fragmentClass.name)
        }
    }

    private fun Page.getBundle(): Bundle? =
        arguments?.let { bundleOf(it) }
}
