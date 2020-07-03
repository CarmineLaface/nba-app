package it.laface.api.models

import com.google.gson.annotations.SerializedName

data class PlayerListResponse(
    @SerializedName("league") val league: NbaLeague
)

data class NbaLeague(
    @SerializedName("standard") val standard: List<NbaPlayer>
)

data class NbaPlayer(
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("personId") val personId: String,
    @SerializedName("teamId") val teamId: String,
    @SerializedName("jersey") val jerseyNumber: String,
    @SerializedName("pos") val position: String
)
