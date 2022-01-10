package it.laface.game.networking

import com.google.gson.annotations.SerializedName
import java.util.Date

class GameData(
    @SerializedName("startTimeUTC") val date: Date,
    @SerializedName("nugget") val nugget: Nugget?,
    @SerializedName("vTeam") val visitorTeam: TeamGameData,
    @SerializedName("hTeam") val homeTeam: TeamGameData,
)

class Nugget(
    @SerializedName("text") val text: String?
)

class TeamGameData(
    @SerializedName("teamId") val teamId: String,
    @SerializedName("triCode") val triCode: String,
    @SerializedName("score") val totalScore: String,
    @SerializedName("linescore") val linescore: List<Score>,
)

class Score(
    @SerializedName("score") val score: String
)
