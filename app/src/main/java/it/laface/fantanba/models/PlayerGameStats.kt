package it.laface.fantanba.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Carmine Laface on 19/12/2018.
 */

class PlayerGameStats(
    @SerializedName("gid") val gameID: String? = null,
    @SerializedName("gdte") val gameDate: String? = null, // in format 2017-04-12
    @SerializedName("tid") val teamId: String? = null,
    @SerializedName("otid") val otherTeamId: String? = null,
    @SerializedName("res") val result: String = "",
    @SerializedName("min") val minutes: Float = 0f,
    @SerializedName("oreb") val offensiveRebounds: Float = 0f,
    @SerializedName("dreb") val defensiveRebounds: Float = 0f,
    @SerializedName("fga") val fieldGoalsAttempted: Int = 0,
    @SerializedName("fgm") val fieldGoalsMade: Int = 0,
    @SerializedName("reb") val totalRebounds: Float = 0f,
    @SerializedName("ast") val assist: Float = 0f,
    @SerializedName("stl") val steals: Float = 0f,
    @SerializedName("blk") val blocks: Float = 0f,
    @SerializedName("tov") val turnovers: Float = 0f,
    @SerializedName("pf") val personalFouls: Float = 0f,
    @SerializedName("pts") val points: Float = 0f,
    @SerializedName("pm") val plusMinus: Int = 0
)
