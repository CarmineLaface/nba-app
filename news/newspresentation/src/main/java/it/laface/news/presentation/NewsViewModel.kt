package it.laface.news.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.base.NetworkResult
import it.laface.common.ContentListToShow
import it.laface.common.ContentToShow
import it.laface.navigation.MessageEmitter
import it.laface.navigation.SnackbarInfo
import it.laface.news.domain.Article
import it.laface.news.domain.BrowserProvider
import it.laface.news.domain.NewsDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val dataSource: NewsDataSource,
    private val browserProvider: BrowserProvider,
    private val jobDispatcher: CoroutineDispatcher,
    private val messageEmitter: MessageEmitter,
) : ViewModel() {

    val contentToShow: MutableStateFlow<ContentListToShow<Article>> =
        MutableStateFlow(ContentToShow.Loading)
    val isRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch(jobDispatcher) {
            val response = dataSource.getNews()
            contentToShow.value = when (response) {
                is NetworkResult.Success ->
                    ContentToShow.Success(response.value)
                is NetworkResult.Failure -> ContentToShow.Error
            }
        }
    }

    fun onRefresh() {
        viewModelScope.launch(jobDispatcher) {
            isRefreshing.value = true
            when (val response = dataSource.getNews()) {
                is NetworkResult.Success ->
                    contentToShow.value = ContentToShow.Success(response.value)
                is NetworkResult.Failure ->
                    messageEmitter.show(SnackbarInfo(it.laface.common.R.string.error))
            }
            isRefreshing.value = false
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
