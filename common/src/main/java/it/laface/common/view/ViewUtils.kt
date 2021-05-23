package it.laface.common.view

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import coil.load

val ViewGroup.inflater: LayoutInflater
    get() = LayoutInflater.from(context)

fun View.goneUnless(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}

fun View.setGone() {
    visibility = View.GONE
}

fun View.setVisible() {
    visibility = View.VISIBLE
}

val Context.isLandScape: Boolean
    get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

fun ImageView.bindImage(uri: String, placeholderResId: Int = 0) {
    load(uri) {
        if (placeholderResId != 0) {
            placeholder(placeholderResId)
            error(placeholderResId)
        }
    }
}
