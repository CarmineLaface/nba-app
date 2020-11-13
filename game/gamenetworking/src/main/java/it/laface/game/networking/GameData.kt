package it.laface.game.networking

import com.google.gson.annotations.SerializedName
import java.util.Date

data class GameData(
    @SerializedName("startTimeUTC") val date: Date,
    @SerializedName("nugget") val nugget: Nugget?,
    @SerializedName("vTeam") val visitorTeam: TeamGameData,
    @SerializedName("hTeam") val homeTeam: TeamGameData,
)

data class Nugget(
    @SerializedName("text") val text: String?
)

data class TeamGameData(
    @SerializedName("teamId") val teamId: String,
    @SerializedName("triCode") val triCode: String,
    @SerializedName("score") val totalScore: String,
    @SerializedName("linescore") val linescore: List<Score>,
)

data class Score(
    @SerializedName("score") val score: String
)
