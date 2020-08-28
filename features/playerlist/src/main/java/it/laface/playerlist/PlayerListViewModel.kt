package it.laface.playerlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.base.CallState
import it.laface.base.NetworkResult
import it.laface.playerlist.domain.PlayersDataSource
import it.laface.domain.model.PlayerModel
import it.laface.player.domain.PlayerDetailPageProvider
import it.laface.navigation.Navigator
import it.laface.stats.domain.StatsPageProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@Suppress("EXPERIMENTAL_API_USAGE")
class PlayerListViewModel(
    private val dataSource: PlayersDataSource,
    private val playerDetailPageProvider: PlayerDetailPageProvider,
    private val navigator: Navigator,
    private val jobDispatcher: CoroutineDispatcher,
    private val statsPageProvider: StatsPageProvider
) : ViewModel() {

    private val playerListCallState: MutableStateFlow<CallState<List<PlayerModel>>> =
        MutableStateFlow(CallState.NotStarted)
    val nameToFilter: MutableStateFlow<String> = MutableStateFlow("")

    val contentToShow: Flow<ContentToShow> =
        playerListCallState.combine(nameToFilter) { callState, nameToFilter ->
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
            playerListCallState.value = when (val response = dataSource.getPlayers()) {
                is NetworkResult.Success -> {
                    CallState.Success(response.value)
                }
                is NetworkResult.Error -> CallState.Error(response.error)
            }
        }
    }

    fun onPlayerSelected(playerModel: PlayerModel) {
        val playerPage = playerDetailPageProvider.getPlayerDetailPage(playerModel)
        navigator.navigateForward(playerPage)
    }

    fun setNameToFilter(text: String) {
        nameToFilter.value = text
    }

    fun goToStatsPage() {
        navigator.navigateForward(statsPageProvider.getStatsPage())
    }
}
