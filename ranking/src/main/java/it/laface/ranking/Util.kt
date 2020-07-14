package it.laface.ranking

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import it.laface.common.view.BaseAdapter

@Suppress("UNCHECKED_CAST")
fun <T> ViewPager2.setPages(vararg pages: T) {
    (adapter as? BaseAdapter<T>)?.run {
        list = pages.toList()
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> RecyclerView.setList(newList: List<T>) {
    (adapter as? BaseAdapter<T>)?.run {
        list = newList
    }
}
