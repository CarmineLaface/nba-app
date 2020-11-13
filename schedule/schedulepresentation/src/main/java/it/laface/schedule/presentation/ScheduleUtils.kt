package it.laface.schedule.presentation

import it.laface.base.CallState
import it.laface.common.ContentListToShow
import it.laface.common.ContentToShow
import it.laface.common.util.isSameDay
import it.laface.game.domain.Game
import kotlinx.coroutines.flow.FlowCollector
import java.util.Date

suspend fun FlowCollector<ContentListToShow<Game>>.getListToShow(date: Date, callState: CallState<List<Game>>) {
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
