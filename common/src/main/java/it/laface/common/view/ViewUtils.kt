package it.laface.common.view

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import it.laface.common.core.applyIf

val ViewGroup.inflater: LayoutInflater
    get() = LayoutInflater.from(context)

fun View.goneUnless(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}

fun Resources.dpToPx(dpValue: Float): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, displayMetrics)

val Context.isLandScape: Boolean
    get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

fun ImageView.bindImage(uri: String, placeholderResId: Int = 0) {
    Glide.with(context)
        .load(uri)
        .applyIf(placeholderResId != 0) {
            apply(RequestOptions.placeholderOf(placeholderResId))
        }
        .into(this)
}
