package it.laface.stats.api

import com.google.gson.annotations.SerializedName

data class PlayerStatsResponse(
    @SerializedName("sections") val sections: List<StatsSectionResponse>
)

data class StatsSectionResponse(
    @SerializedName("players") val players: List<PlayerResponse>,
    @SerializedName("title") val title: String
)

data class PlayerResponse(
    @SerializedName("playerId") val playerId: Int,
    @SerializedName("playerName") val playerName: String,
    @SerializedName("teamId") val teamId: Int,
    @SerializedName("value") val value: String
)
