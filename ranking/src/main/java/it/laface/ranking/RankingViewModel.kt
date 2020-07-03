package it.laface.ranking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.domain.CallState
import it.laface.domain.NetworkResult
import it.laface.domain.RankingDataSource
import it.laface.domain.RankingLists
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Suppress("EXPERIMENTAL_API_USAGE")
class RankingViewModel(
    private val dataSource: RankingDataSource,
    private val jobDispatcher: CoroutineDispatcher
) : ViewModel() {

    val rankingListsCallState: MutableStateFlow<CallState<RankingLists>> =
        MutableStateFlow(CallState.NotStarted)

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch(jobDispatcher) {
            rankingListsCallState.value = when (val response = dataSource.getRanking()) {
                is NetworkResult.Success -> {
                    CallState.Success(response.value)
                }
                is NetworkResult.Error -> CallState.Error(response.error)
            }
        }
    }
}