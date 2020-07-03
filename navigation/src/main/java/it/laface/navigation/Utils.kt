package it.laface.navigation

import android.view.View

typealias SimpleCallback = () -> Unit

fun SimpleCallback.toClickListener(): View.OnClickListener =
    View.OnClickListener { invoke() }
