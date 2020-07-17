package it.laface.news

import android.os.Build
import android.text.Html
import it.laface.common.util.getFullDayName
import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.domain.model.Article
import it.laface.news.databinding.ItemNewsBinding

class ArticleViewHolder(
    private val binding: ItemNewsBinding,
    onItemClicked: (Article) -> Unit
) : BaseViewHolder<Article>(binding.root, onItemClicked) {

    override fun bind(item: Article) {
        super.bind(item)
        binding.titleTextView.text = item.title
        binding.imageView.bindImage(item.imageUrl, R.drawable.placeholder_image)
        val htmlSource = item.htmlContent
        binding.bodyTextView.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(htmlSource, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(htmlSource)
        }
        binding.dateTextView.text = item.date.getFullDayName
    }
}
