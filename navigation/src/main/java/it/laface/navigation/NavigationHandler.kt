package it.laface.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import it.laface.common.ActivityProvider
import it.laface.common.navigation.EventType
import it.laface.common.navigation.NavigationType
import it.laface.common.navigation.Navigator
import it.laface.common.navigation.Page

class NavigationHandler(
    private val activityProvider: ActivityProvider,
    private val containerViewResId: Int
) : Navigator {

    private val manager: FragmentManager?
        get() = (activityProvider.currentActivity as? FragmentActivity)?.supportFragmentManager

    override fun navigate(navigation: EventType.Navigation) {
        when (val type = navigation.type) {
            is NavigationType.Replace ->
                replace(type.destination, type.addToStack)
            is NavigationType.Add ->
                add(type.destination, type.addToStack)
            NavigationType.Back ->
                manager?.popBackStack()
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
