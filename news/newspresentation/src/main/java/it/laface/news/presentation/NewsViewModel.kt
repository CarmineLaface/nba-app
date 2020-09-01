package it.laface.news.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.base.NetworkResult
import it.laface.common.ContentToShow
import it.laface.news.domain.Article
import it.laface.news.domain.BrowserProvider
import it.laface.news.domain.NewsDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Suppress("EXPERIMENTAL_API_USAGE")
class NewsViewModel(
    private val dataSource: NewsDataSource,
    private val browserProvider: BrowserProvider,
    private val jobDispatcher: CoroutineDispatcher
) : ViewModel() {

    val contentToShow: MutableStateFlow<ContentToShow<Article>> = MutableStateFlow(ContentToShow.Loading)

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch(jobDispatcher) {
            contentToShow.value = when (val response = dataSource.getNews()) {
                is NetworkResult.Success -> {
                    ContentToShow.Success(response.value)
                }
                is NetworkResult.Error -> ContentToShow.Error
            }
        }
    }

    fun onRefresh() {
        viewModelScope.launch(jobDispatcher) {
            val result = dataSource.getNews()
            if (result is NetworkResult.Success) {
                contentToShow.value = ContentToShow.Success(result.value)
            }
        }
    }

    fun onRetry() {
        contentToShow.value = ContentToShow.Loading
        getNews()
    }

    fun openNews(article: Article) {
        browserProvider.openWebPage(article.link)
    }
}
