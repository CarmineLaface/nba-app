package it.laface.domain.datasource

import it.laface.domain.model.Game

interface ScheduleDataSource {

    suspend fun getSchedule(): List<Game>
}