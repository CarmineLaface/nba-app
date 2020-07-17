package it.laface.schedule

import it.laface.common.util.isSameDay
import it.laface.domain.CallState
import it.laface.domain.model.Game
import kotlinx.coroutines.flow.FlowCollector
import java.util.Date

suspend fun FlowCollector<ContentToShow>.getListToShow(date: Date, callState: CallState<List<Game>>) {
    val contentToShow = when (callState) {
        is CallState.Success -> {
            val filteredList = callState.result.filter { it.date isSameDay date }
            if (filteredList.isEmpty()) {
                ContentToShow.Placeholder
            } else {
                ContentToShow.Success(filteredList)
            }
        }
        is CallState.Error -> ContentToShow.Error
        CallState.InProgress, CallState.NotStarted -> ContentToShow.Loading
    }
    emit(contentToShow)
}
