package it.laface.common.util

import android.os.Parcelable
import androidx.fragment.app.Fragment

fun <T : Parcelable> Fragment.requireParcelable(key: String): T =
    requireArguments().getParcelable(key) ?: throw IllegalStateException("argument is null")
