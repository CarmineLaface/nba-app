package it.laface.navigation

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import it.laface.common.ActivityProvider

class NavigationHandler(
    private val activityProvider: ActivityProvider,
    private val containerViewResId: Int
) : Navigator {

    private val manager: FragmentManager?
        get() = (activityProvider.currentActivity as? FragmentActivity)?.supportFragmentManager

    override fun navigateBack() {
        manager?.popBackStack()
    }

    @Suppress("UNCHECKED_CAST")
    override fun navigateTo(destination: Page, addToStack: Boolean) {
        val fragmentClass = destination.fragmentClass as? Class<out Fragment> ?: return
        manager?.commit {
            replace(containerViewResId, fragmentClass, destination.getBundle(), fragmentClass.name)
            if (addToStack) {
                addToBackStack(fragmentClass.name)
            }
        }
    }

    override fun clearStack() {
        val manager = manager ?: return
        repeat(manager.backStackEntryCount) {
            manager.popBackStack()
        }
    }

    private fun Page.getBundle(): Bundle? =
        arguments?.let { bundleOf(it) }
}
