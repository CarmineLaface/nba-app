package it.laface.game.domain

import android.os.Parcelable
import it.laface.domain.model.Team
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Game(
    val id: String,
    val date: Date,
    val gameDateFormatted: String,
    val homeTeam: Team,
    val visitorTeam: Team,
    val homeScore: String?,
    val visitorScore: String?
) : Parcelable
