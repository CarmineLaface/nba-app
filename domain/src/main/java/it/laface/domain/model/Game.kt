package it.laface.domain.model

import java.util.Date

data class Game(
    val date: Date,
    val homeTeam: RankedTeam,
    val visitorTeam: RankedTeam
)
