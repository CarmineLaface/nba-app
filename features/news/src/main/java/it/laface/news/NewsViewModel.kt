package it.laface.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.domain.browser.BrowserProvider
import it.laface.domain.network.NetworkResult
import it.laface.domain.datasource.NewsDataSource
import it.laface.domain.model.Article
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Suppress("EXPERIMENTAL_API_USAGE")
class NewsViewModel(
    private val dataSource: NewsDataSource,
    private val browserProvider: BrowserProvider,
    private val jobDispatcher: CoroutineDispatcher
) : ViewModel() {

    val contentToShow: MutableStateFlow<ContentToShow> = MutableStateFlow(ContentToShow.Loading)

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
