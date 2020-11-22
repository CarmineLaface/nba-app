package it.laface.game.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.base.CallState
import it.laface.base.CallState.InProgress
import it.laface.base.NetworkResult
import it.laface.common.ContentToShow
import it.laface.game.domain.Game
import it.laface.game.domain.GameDataSource
import it.laface.game.domain.GameInfo
import it.laface.navigation.Navigator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Suppress("EXPERIMENTAL_API_USAGE")
class GameViewModel(
    val game: Game,
    private val navigator: Navigator,
    private val gameDataSource: GameDataSource,
    private val jobDispatcher: CoroutineDispatcher
) : ViewModel() {

    val gameInfoCallState: MutableStateFlow<ContentToShow<GameInfo>>

    init {
        if (game.homeScore != null) {
            gameInfoCallState = MutableStateFlow(ContentToShow.Loading)
            getGameInfo()
        } else {
            gameInfoCallState = MutableStateFlow(ContentToShow.Placeholder)
        }
    }

    private fun getGameInfo() {
        viewModelScope.launch(jobDispatcher) {
            val response = gameDataSource.getInfo(game.gameDateFormatted, game.id)
            gameInfoCallState.value = when (response) {
                is NetworkResult.Success ->
                    ContentToShow.Success(response.value)
                is NetworkResult.Failure ->
                    ContentToShow.Error
            }
        }
    }

    fun navigateBack() {
        navigator.navigateBack()
    }
}
