package it.laface.player.api

import com.google.gson.annotations.SerializedName

class PlayerStatsResponse(
    @SerializedName("league") val league: League
)

class League(
    @SerializedName("standard") val standard: Standard
)

class Standard(
    @SerializedName("stats") val stats: Stats
)

class Stats(
    @SerializedName("latest") val latest: Latest
)

@Suppress("LongParameterList")
class Latest(
    @SerializedName("ppg") val pointsPerGame: String,
    @SerializedName("rpg") val reboundsPerGame: String,
    @SerializedName("apg") val assistsPerGame: String,
    @SerializedName("mpg") val minutesPerGame: String,
    @SerializedName("bpg") val blocksPerGame: String,
    @SerializedName("fgp") val fieldGoalPercentage: String,
    @SerializedName("tpp") val threePointFieldGoalPercentage: String,
    @SerializedName("ftp") val freeThrowPercentage: String,
)
