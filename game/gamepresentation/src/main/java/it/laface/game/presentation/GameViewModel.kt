package it.laface.game.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.base.CallState
import it.laface.base.CallState.InProgress
import it.laface.base.NetworkResult
import it.laface.game.domain.Game
import it.laface.game.domain.GameDataSource
import it.laface.game.domain.GameInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Suppress("EXPERIMENTAL_API_USAGE")
class GameViewModel(
    val game: Game,
    private val gameDataSource: GameDataSource,
    private val jobDispatcher: CoroutineDispatcher
) : ViewModel() {

    val gameInfoCallState: MutableStateFlow<CallState<GameInfo>> =
        MutableStateFlow(InProgress)

    init {
        if (game.homeScore != null) {
            getGameInfo()
        }
    }

    private fun getGameInfo() {
        viewModelScope.launch(jobDispatcher) {
            val response = gameDataSource.getInfo(game.gameDateFormatted, game.id)
            gameInfoCallState.value = when (response) {
                is NetworkResult.Success ->
                    CallState.Success(response.value)
                is NetworkResult.Failure -> CallState.Error(response.error)
            }
        }
    }
}
