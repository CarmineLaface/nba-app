package it.laface.news.presentation

import android.text.Html
import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.news.domain.Article
import it.laface.news.presentation.databinding.ItemNewsBinding

class ArticleViewHolder(
    private val binding: ItemNewsBinding,
    private val onItemClicked: (Article) -> Unit
) : BaseViewHolder<Article>(binding.root) {

    override fun bind(item: Article) {
        itemView.setOnClickListener { onItemClicked.invoke(item) }
        binding.titleTextView.text = item.title
        binding.imageView.bindImage(item.imageUrl, R.drawable.placeholder_image)
        val htmlSource = item.htmlContent
        binding.bodyTextView.text = Html.fromHtml(htmlSource, Html.FROM_HTML_MODE_LEGACY)
    }
}
