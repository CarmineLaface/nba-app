package it.laface.team.api

import com.google.gson.annotations.SerializedName

data class TeamRosterResponse(
    @SerializedName("t") val roasterInfo: RoasterInfo
)

data class RoasterInfo(
    @SerializedName("tid") val teamId: Int,
    @SerializedName("ta") val teamTricode: String,
    @SerializedName("tn") val teamName: String,
    @SerializedName("tc") val teamCity: String,
    @SerializedName("pl") val players: List<PlayerResponse>
)

data class PlayerResponse(
    @SerializedName("fn") val name: String,
    @SerializedName("ln") val surname: String,
    @SerializedName("pid") val id: Int,
    @SerializedName("num") val jerseyNumber: String,
    @SerializedName("pos") val position: String
)
