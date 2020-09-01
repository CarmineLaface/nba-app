package it.laface.navigation

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.material.snackbar.BaseTransientBottomBar
import it.laface.navigation.SnackbarDuration.Indefinite
import it.laface.navigation.SnackbarDuration.Long
import it.laface.navigation.SnackbarDuration.Short

fun Page.getBundle(): Bundle? =
    arguments?.let { bundleOf(it) }

typealias SimpleCallback = () -> Unit

fun SimpleCallback.toClickListener(): View.OnClickListener =
    View.OnClickListener { invoke() }

val SnackbarDuration.durationRef: Int
    get() = when (this) {
        Indefinite -> BaseTransientBottomBar.LENGTH_INDEFINITE
        Short -> BaseTransientBottomBar.LENGTH_SHORT
        Long -> BaseTransientBottomBar.LENGTH_LONG
    }

fun Activity.getBaseView(): View? =
    (findViewById(android.R.id.content) as? ViewGroup)
        ?.getChildAt(0)
