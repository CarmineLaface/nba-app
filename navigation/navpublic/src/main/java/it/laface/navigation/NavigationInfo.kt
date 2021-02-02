package it.laface.navigation

sealed class NavigationInfo {

    data class Forward(
        val destination: Page
    ) : NavigationInfo()

    object Back : NavigationInfo()
}

