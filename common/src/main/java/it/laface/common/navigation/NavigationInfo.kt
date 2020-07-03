package it.laface.common.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment

sealed class NavigationType {

    data class Add(val addToStack: Boolean) : NavigationType()
    data class Replace(val addToStack: Boolean) : NavigationType()
    object Back : NavigationType()
}

data class Page(
    val fragmentClass: Class<Fragment>,
    val arguments: Bundle,
    val tag: String? = null
)
