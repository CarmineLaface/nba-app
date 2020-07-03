package it.laface.common.view

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

val ViewGroup.inflater: LayoutInflater
    get() = LayoutInflater.from(context)

fun ViewGroup.inflate(@LayoutRes layoutResId: Int): View =
    inflater.inflate(layoutResId, this, false)

@Suppress("UNCHECKED_CAST")
fun <T> RecyclerView.setList(newList: List<T>) {
    (adapter as? BaseAdapter<T>)?.run {
        list = newList
    }
}

fun View.goneUnless(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}

val Context.isLandScape: Boolean
    get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
