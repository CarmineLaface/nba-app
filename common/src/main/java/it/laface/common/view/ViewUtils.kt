package it.laface.common.view

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

val ViewGroup.inflater: LayoutInflater
    get() = LayoutInflater.from(context)

fun ViewGroup.inflate(@LayoutRes layoutResId: Int): View =
    inflater.inflate(layoutResId, this, false)

fun View.goneUnless(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}

val Context.isLandScape: Boolean
    get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

fun ImageView.bindImage(uri: String, placeholderResId: Int = 0) {
    Glide.with(context)
        .load(uri)
        .apply {
            if (placeholderResId != 0) {
                apply(RequestOptions.placeholderOf(placeholderResId))
            }
        }
        .into(this)
}
