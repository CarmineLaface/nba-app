package it.laface.schedule

import it.laface.common.util.isSameDay
import it.laface.domain.CallState
import it.laface.domain.model.Game
import kotlinx.coroutines.flow.FlowCollector
import java.util.Date

suspend fun FlowCollector<List<Game>>.getListToShow(date: Date, callState: CallState<List<Game>>) {
    val filteredList = if (callState is CallState.Success) {
        callState.result.filter { it.date isSameDay date }
    } else {
        emptyList()
    }
    emit(filteredList)
}
