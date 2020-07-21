package it.laface.common.navigation

import it.laface.common.navigation.EventType.Navigation

interface Navigator {

    fun navigate(navigation: Navigation)

    fun navigateBack() {
        navigate(Navigation(NavigationType.Back))
    }
}
