package it.laface.domain.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import it.laface.domain.navigation.ForwardNavigationType.Replace

sealed class NavigationInfo {

    data class Forward(
        val destination: Page,
        val type: ForwardNavigationType = Replace,
        val addToStack: Boolean = true
    ) : NavigationInfo()

    object Back : NavigationInfo()
}

enum class ForwardNavigationType {
    Add,
    Replace
}

data class Page(
    val fragmentClass: Class<Fragment>,
    val arguments: Bundle? = null,
    val tag: String? = null
)
