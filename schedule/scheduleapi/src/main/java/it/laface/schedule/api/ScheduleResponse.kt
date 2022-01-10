package it.laface.schedule.api

import com.google.gson.annotations.SerializedName
import java.util.Date

class ScheduleResponse(
    @SerializedName("league") val league: ScheduleLeague
)

class ScheduleLeague(
    @SerializedName("standard") val standardGameList: List<GameResponse>,
)

@Suppress("LongParameterList")
class GameResponse(
    @SerializedName("gameId") val gameId: String,
    @SerializedName("seasonStageId") val seasonStageId: Int,
    @SerializedName("gameUrlCode") val gameUrlCode: String,
    @SerializedName("startTimeUTC") val date: Date,
    @SerializedName("startDateEastern") val startDateEastern: String,
    @SerializedName("hTeam") val homeTeam: IdTeam,
    @SerializedName("vTeam") val visitorTeam: IdTeam
)

class IdTeam(
    @SerializedName("teamId") val teamId: String,
    @SerializedName("score") val score: String? = null
)
