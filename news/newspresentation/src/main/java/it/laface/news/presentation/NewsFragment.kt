package it.laface.news.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import it.laface.common.ContentListToShow
import it.laface.common.ContentToShow
import it.laface.common.util.observe
import it.laface.common.view.BaseAdapter
import it.laface.common.view.goneUnless
import it.laface.common.view.inflater
import it.laface.common.viewModels
import it.laface.navigation.MessageEmitter
import it.laface.news.domain.Article
import it.laface.news.domain.BrowserProvider
import it.laface.news.domain.NewsDataSource
import it.laface.news.presentation.databinding.FragmentNewsBinding
import it.laface.news.presentation.databinding.ItemNewsBinding
import kotlinx.coroutines.Dispatchers

class NewsFragment(
    dataSource: NewsDataSource,
    browserProvider: BrowserProvider,
    messageEmitter: MessageEmitter,
) : Fragment() {

    private val viewModel: NewsViewModel by viewModels {
        NewsViewModel(
            dataSource = dataSource,
            browserProvider = browserProvider,
            jobDispatcher = Dispatchers.IO,
            messageEmitter = messageEmitter,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentNewsBinding
            .inflate(inflater, container, false)
            .apply {
                setView()
            }.root

    private fun FragmentNewsBinding.setView() {
        val newsAdapter = getNewsAdapter()
        newsRecyclerView.adapter = newsAdapter

        observe(viewModel.contentToShow) { contentToShow ->
            bindContentToShow(contentToShow, newsAdapter)
        }
        retryButton.setOnClickListener {
            viewModel.onRetry()
        }
        swipeRefreshLayout.setOnRefreshListener(viewModel::onRefresh)
        observe(viewModel.isRefreshing) { isRefreshing ->
            swipeRefreshLayout.isRefreshing = isRefreshing
        }
    }

    private fun FragmentNewsBinding.bindContentToShow(
        contentToShow: ContentListToShow<Article>,
        newsAdapter: BaseAdapter<Article>
    ) {
        if (contentToShow is ContentToShow.Success) {
            newsRecyclerView.visibility = View.VISIBLE
            newsAdapter.list = contentToShow.content
        } else {
            newsRecyclerView.visibility = View.GONE
        }
        progressBar.goneUnless(contentToShow is ContentToShow.Loading)
        retryButton.goneUnless(contentToShow is ContentToShow.Error)
    }

    private fun getNewsAdapter(): BaseAdapter<Article> =
        BaseAdapter { parent ->
            ArticleViewHolder(
                ItemNewsBinding.inflate(parent.inflater, parent, false),
                viewModel::openNews
            )
        }
}
