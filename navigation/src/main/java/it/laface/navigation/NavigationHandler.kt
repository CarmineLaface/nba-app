package it.laface.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import it.laface.common.ActivityProvider
import it.laface.domain.navigation.ForwardNavigationType.Add
import it.laface.domain.navigation.ForwardNavigationType.Replace
import it.laface.domain.navigation.NavigationInfo
import it.laface.domain.navigation.Navigator
import it.laface.domain.navigation.Page

class NavigationHandler(
    private val activityProvider: ActivityProvider,
    private val containerViewResId: Int
) : Navigator {

    private val manager: FragmentManager?
        get() = (activityProvider.currentActivity as? FragmentActivity)?.supportFragmentManager

    override fun navigate(navigation: NavigationInfo) {
        when (navigation) {
            is NavigationInfo.Forward -> navigateForward(navigation)
            NavigationInfo.Back -> manager?.popBackStack()
        }
    }

    private fun navigateForward(navigation: NavigationInfo.Forward) {
        when (navigation.type) {
            Add -> add(navigation.destination, navigation.addToStack)
            Replace -> replace(navigation.destination, navigation.addToStack)
        }
    }

    private fun replace(page: Page, addToBackStack: Boolean) {
        manager?.commit {
            replace(containerViewResId, page.fragmentClass, page.arguments, page.tag)
            if (addToBackStack) {
                addToBackStack(page.tag)
            }
        }
    }

    private fun add(page: Page, addToBackStack: Boolean) {
        manager?.commit {
            add(containerViewResId, page.fragmentClass, page.arguments, page.tag)
            if (addToBackStack) {
                addToBackStack(page.tag)
            }
        }
    }
}
