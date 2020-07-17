package it.laface.api.models

import com.google.gson.annotations.SerializedName
import java.util.Date

class ScheduleResponse(
    @SerializedName("league") val league: ScheduleLeague
)

data class ScheduleLeague(
    @SerializedName("standard") val gameList: List<GameResponse>
)

data class GameResponse(
    @SerializedName("gameId") val gameId: String,
    @SerializedName("seasonStageId") val seasonStageId: Int,
    @SerializedName("gameUrlCode") val gameUrlCode: String,
    @SerializedName("startTimeUTC") val date: Date,
    @SerializedName("hTeam") val homeTeam: IdTeam,
    @SerializedName("vTeam") val visitorTeam: IdTeam
)

data class IdTeam(
    @SerializedName("teamId") val teamId: String
)
