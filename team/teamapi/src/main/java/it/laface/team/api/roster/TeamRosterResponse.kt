package it.laface.team.api.roster

import com.google.gson.annotations.SerializedName

class TeamRosterResponse(
    @SerializedName("t") val roasterInfo: RoasterInfo
)

class RoasterInfo(
    @SerializedName("tid") val teamId: Int,
    @SerializedName("ta") val teamTricode: String,
    @SerializedName("tn") val teamName: String,
    @SerializedName("tc") val teamCity: String,
    @SerializedName("pl") val players: List<PlayerResponse>
)

class PlayerResponse(
    @SerializedName("fn") val name: String,
    @SerializedName("ln") val surname: String,
    @SerializedName("pid") val id: Int,
    @SerializedName("num") val jerseyNumber: String,
    @SerializedName("pos") val position: String
)
