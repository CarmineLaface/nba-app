package it.laface.common.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter<T>(
    private val viewHolderProvider: (ViewGroup) -> BaseViewHolder<T>
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    var list: List<T> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> =
        viewHolderProvider(parent)
}
