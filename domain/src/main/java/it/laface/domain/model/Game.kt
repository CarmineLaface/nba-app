package it.laface.domain.model

import java.util.Date

data class Game (
    val date: Date,
    val homeTeam: Team,
    val visitorTeam: Team
)

data class Team(
    val id: String,
    val code: String,
    val name: String
)