package it.laface.playerlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.base.CallState
import it.laface.base.NetworkResult
import it.laface.common.ContentListToShow
import it.laface.navigation.Navigator
import it.laface.player.domain.Player
import it.laface.player.domain.PlayerDetailPageProvider
import it.laface.player.domain.PlayersDataSource
import it.laface.stats.domain.StatsPageProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class PlayerListViewModel(
    private val dataSource: PlayersDataSource,
    private val playerDetailPageProvider: PlayerDetailPageProvider,
    private val navigator: Navigator,
    private val jobDispatcher: CoroutineDispatcher,
    private val statsPageProvider: StatsPageProvider
) : ViewModel() {

    private val playerListCallState: MutableStateFlow<CallState<List<Player>>> =
        MutableStateFlow(CallState.NotStarted)
    val nameToFilter: MutableStateFlow<String> = MutableStateFlow("")

    val contentToShow: Flow<ContentListToShow<Player>> =
        combine(playerListCallState, nameToFilter) { callState, nameToFilter ->
            mapContentToShow(callState, nameToFilter)
        }

    init {
        getPlayers()
    }

    fun onRetry() {
        playerListCallState.value = CallState.InProgress
        getPlayers()
    }

    private fun getPlayers() {
        viewModelScope.launch(jobDispatcher) {
            playerListCallState.value =
                when (val response = dataSource.getPlayers()) {
                    is NetworkResult.Success ->
                        CallState.Success(response.value)
                    is NetworkResult.Failure -> CallState.Error(response.error)
                }
        }
    }

    fun onPlayerSelected(player: Player) {
        val playerPage = playerDetailPageProvider.getPlayerDetailPage(player)
        navigator.navigateTo(playerPage)
    }

    fun setNameToFilter(text: String) {
        nameToFilter.value = text
    }

    fun goToStatsPage() {
        navigator.navigateTo(statsPageProvider.getStatsPage())
    }
}
