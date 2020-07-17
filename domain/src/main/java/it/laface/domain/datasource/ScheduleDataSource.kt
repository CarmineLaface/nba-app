package it.laface.domain.datasource

import it.laface.domain.NetworkResult
import it.laface.domain.model.Game

interface ScheduleDataSource {

    suspend fun getSchedule(): NetworkResult<List<Game>>
}