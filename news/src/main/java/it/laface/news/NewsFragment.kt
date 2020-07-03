package it.laface.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import it.laface.common.ActivityRegister
import it.laface.common.util.BrowserProviderImpl
import it.laface.common.view.BaseAdapter
import it.laface.common.view.inflater
import it.laface.common.viewModels
import it.laface.domain.Article
import it.laface.domain.NewsDataSource
import it.laface.news.databinding.FragmentNewsBinding
import it.laface.news.databinding.ItemNewsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsFragment(dataSource: NewsDataSource) : Fragment() {

    private val viewModel: NewsViewModel by viewModels {
        NewsViewModel(
            dataSource,
            BrowserProviderImpl(ActivityRegister),
            Dispatchers.IO
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getNews()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentNewsBinding
            .inflate(inflater, container, false)
            .apply {
                lifecycleScope.launch { setView() }
            }.root

    private suspend fun FragmentNewsBinding.setView() {
        val adapter = getNewsAdapter()
        newsRecyclerView.adapter = adapter

        viewModel.contentToShow.collect {
            if (it is ContentToShow.Success) {
                adapter.list = it.filteredList
            }
        }
        retryButton.setOnClickListener {
            viewModel.onRetry()
        }
        swipeRefreshLayout.setOnRefreshListener(viewModel::onRefresh)
    }

    private fun getNewsAdapter(): BaseAdapter<Article> =
        BaseAdapter { parent ->
            ArticleViewHolder(
                ItemNewsBinding.inflate(parent.inflater, parent, false),
                viewModel::openNews
            )
        }
}
