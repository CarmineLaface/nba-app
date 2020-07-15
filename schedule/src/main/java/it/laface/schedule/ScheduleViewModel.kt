package it.laface.schedule

import androidx.lifecycle.ViewModel
import it.laface.domain.datasource.ScheduleDataSource
import kotlinx.coroutines.CoroutineDispatcher

class ScheduleViewModel(
    private val dataSource: ScheduleDataSource,
    private val jobDispatcher: CoroutineDispatcher
) : ViewModel() {

}