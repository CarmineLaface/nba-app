package it.laface.domain.navigation

interface Navigator {

    fun navigate(navigation: NavigationInfo)

    fun navigateBack() {
        navigate(NavigationInfo.Back)
    }
}
