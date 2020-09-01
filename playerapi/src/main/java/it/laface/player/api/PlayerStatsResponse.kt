package it.laface.player.api

import com.google.gson.annotations.SerializedName

class PlayerStatsResponse(
    @SerializedName("league") val league: League
)

data class League(
    @SerializedName("standard") val standard: Standard
)

data class Standard(
    @SerializedName("stats") val stats: Stats
)

data class Stats(
    @SerializedName("latest") val latest: Latest
)

data class Latest(
    @SerializedName("ppg") val pointsPerGame: String,
    @SerializedName("rpg") val reboundsPerGame: String,
    @SerializedName("apg") val assistsPerGame: String,
)
