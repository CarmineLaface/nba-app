package it.laface.navigation

sealed class NavigationInfo {

    data class Forward(
        val destination: Page,
        val type: ForwardNavigationType = ForwardNavigationType.Replace,
        val addToStack: Boolean = true
    ) : NavigationInfo()

    object Back : NavigationInfo()
}

enum class ForwardNavigationType {
    Add,
    Replace
}
