package it.laface.common.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in T>(
    itemView: View,
    private val onItemClicked: ((T) -> Unit)? = null
) : RecyclerView.ViewHolder(itemView) {

    open fun bind(item: T) {
        if (onItemClicked != null) {
            itemView.setOnClickListener { onItemClicked.invoke(item) }
        }
    }
}
