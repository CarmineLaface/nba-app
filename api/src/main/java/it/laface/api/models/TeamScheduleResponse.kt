package it.laface.api.models

import com.google.gson.annotations.SerializedName

data class TeamScheduleResponse(
    @SerializedName("league") val league: ScheduleLeague
)

data class TeamScheduleLeague(
    @SerializedName("standard") val standardGameList: List<GameResponse>,
    @SerializedName("sacramento") val sacramentoGameList: List<GameResponse>,
    @SerializedName("vegas") val vegasGameList: List<GameResponse>,
    @SerializedName("utah") val utahGameList: List<GameResponse>
)
