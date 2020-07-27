package it.laface.domain.model

import java.util.Date

data class Game(
    val date: Date,
    val homeTeam: NbaTeam,
    val visitorTeam: NbaTeam,
    val homeScore: String? = null,
    val visitorScore: String? = null
)
