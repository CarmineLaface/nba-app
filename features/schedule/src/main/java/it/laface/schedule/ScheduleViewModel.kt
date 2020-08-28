package it.laface.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.base.CallState
import it.laface.base.NetworkResult
import it.laface.schedule.domain.Game
import it.laface.schedule.domain.ScheduleDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.launch
import java.util.Date

@Suppress("EXPERIMENTAL_API_USAGE")
class ScheduleViewModel(
    private val dataSource: ScheduleDataSource,
    jobDispatcher: CoroutineDispatcher
) : ViewModel() {

    val selectedDate: MutableStateFlow<Date> = MutableStateFlow(Date())
    private val scheduleCallState: MutableStateFlow<CallState<List<Game>>> =
        MutableStateFlow(CallState.InProgress)

    val gamesToShow: Flow<ContentToShow> =
        selectedDate.combineTransform(scheduleCallState) { date, callState ->
            getListToShow(date, callState)
        }

    init {
        viewModelScope.launch(jobDispatcher) {
            scheduleCallState.value = when (val response = dataSource.getLeagueSchedule()) {
                is NetworkResult.Success ->
                    CallState.Success(response.value)
                is NetworkResult.Error ->
                    CallState.Error(response.error)
            }
        }
    }
}
