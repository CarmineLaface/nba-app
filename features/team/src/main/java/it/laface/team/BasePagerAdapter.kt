package it.laface.team

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class BasePagerAdapter<T>(
    private val pageWidth: Float = 1F,
    private val viewHolderProvider: (ViewGroup) -> PageViewHolder<T>
): PagerAdapter() {

    var list: List<T> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getCount(): Int = list.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) = container.removeView(`object` as View)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val viewHolder = viewHolderProvider(container)

        viewHolder.bind(list[position])

        container.addView(viewHolder.view)
        return viewHolder.view
    }

    override fun getPageWidth(position: Int): Float = pageWidth
}

abstract class PageViewHolder<T> {

    abstract val view: View

    abstract fun bind(item: T)
}