package it.laface.schedule.domain

import it.laface.domain.model.Team
import java.util.Date

data class Game(
    val date: Date,
    val homeTeam: Team,
    val visitorTeam: Team,
    val homeScore: String?,
    val visitorScore: String?
)
