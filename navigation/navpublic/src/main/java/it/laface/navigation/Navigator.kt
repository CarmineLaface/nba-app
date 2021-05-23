package it.laface.navigation

interface Navigator {

    fun navigateBack()

    fun navigateTo(destination: Page, addToStack: Boolean = true)

    fun clearStack()
}
