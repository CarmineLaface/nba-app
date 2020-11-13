package it.laface.schedule.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.base.CallState
import it.laface.base.NetworkResult
import it.laface.common.ContentListToShow
import it.laface.game.domain.Game
import it.laface.game.domain.GamePageProvider
import it.laface.game.domain.ScheduleDataSource
import it.laface.navigation.Navigator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.launch
import java.util.Date

@Suppress("EXPERIMENTAL_API_USAGE")
class ScheduleViewModel(
    private val dataSource: ScheduleDataSource,
    private val gamePageProvider: GamePageProvider,
    private val navigator: Navigator,
    jobDispatcher: CoroutineDispatcher
) : ViewModel() {

    val selectedDate: MutableStateFlow<Date> = MutableStateFlow(Date())
    private val scheduleCallState: MutableStateFlow<CallState<List<Game>>> =
        MutableStateFlow(CallState.InProgress)

    val gamesToShow: Flow<ContentListToShow<Game>> =
        selectedDate.combineTransform(scheduleCallState) { date, callState ->
            getListToShow(date, callState)
        }

    init {
        viewModelScope.launch(jobDispatcher) {
            val response = dataSource.getLeagueSchedule()
            scheduleCallState.value = when (response) {
                is NetworkResult.Success ->
                    CallState.Success(response.value)
                is NetworkResult.Failure ->
                    CallState.Error(response.error)
            }
        }
    }

    fun onGameSelected(item: Game) {
        val gamePage = gamePageProvider.getGamePage(item)
        navigator.navigateForward(gamePage)
    }
}
