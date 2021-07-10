package it.laface.common.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import coil.load

val ViewGroup.inflater: LayoutInflater
    get() = LayoutInflater.from(context)

fun ImageView.bindImage(uri: String, placeholderResId: Int = 0) {
    load(uri) {
        if (placeholderResId != 0) {
            placeholder(placeholderResId)
            error(placeholderResId)
        }
    }
}
