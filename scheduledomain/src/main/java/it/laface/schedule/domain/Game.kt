package it.laface.schedule.domain

import it.laface.domain.model.NbaTeam
import java.util.Date

data class Game(
    val date: Date,
    val homeTeam: NbaTeam,
    val visitorTeam: NbaTeam,
    val homeScore: String?,
    val visitorScore: String?
)
