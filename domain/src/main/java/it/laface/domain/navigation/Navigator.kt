package it.laface.domain.navigation

interface Navigator {

    fun navigate(navigation: NavigationInfo)

    fun navigateBack() {
        navigate(NavigationInfo.Back)
    }

    fun navigateForward(page: Page) {
        navigate(NavigationInfo.Forward(page))
    }
}
