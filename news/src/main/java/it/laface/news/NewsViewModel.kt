package it.laface.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.common.util.BrowserProvider
import it.laface.domain.Article
import it.laface.domain.NetworkResult
import it.laface.domain.NewsDataSource
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

    fun getNews() {
        contentToShow.value = ContentToShow.Loading
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
        getNews()
    }

    fun openNews(article: Article) {
        browserProvider.openWebPage(article.link)
    }
}
