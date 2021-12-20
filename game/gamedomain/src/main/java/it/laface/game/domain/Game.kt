package it.laface.game.domain

import it.laface.domain.model.Team
import java.io.Serializable
import java.util.Date

data class Game(
    val id: String,
    val date: Date,
    val gameDateFormatted: String,
    val homeTeam: Team,
    val visitorTeam: Team,
    val homeScore: String?,
    val visitorScore: String?
) : Serializable {

    companion object {
        const val serialVersionUID = 126L
    }
}
