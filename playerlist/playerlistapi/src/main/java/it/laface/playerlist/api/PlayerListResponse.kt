package it.laface.playerlist.api

import com.google.gson.annotations.SerializedName

class PlayerListResponse(
    @SerializedName("league") val league: NbaLeague
)

class NbaLeague(
    @SerializedName("standard") val standard: List<NbaPlayer>
)

class NbaPlayer(
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("personId") val personId: String,
    @SerializedName("teamId") val teamId: String,
    @SerializedName("jersey") val jerseyNumber: String,
    @SerializedName("pos") val position: String
)
