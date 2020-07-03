package it.laface.api.models

import com.google.gson.annotations.SerializedName

data class RankingResponse(
    val league: League
)

data class League(
    val standard: Standard
)

data class Standard(
    val conference: Conference
)

data class Conference(
    val east: List<NbaTeam>,
    val west: List<NbaTeam>
)

data class NbaTeam(
    @SerializedName("teamId") val id: String,
    val win: String,
    val loss: String,
    @SerializedName("confRank") val rankingPosition: String,
    @SerializedName("teamSitesOnly") val info: TeamInfo
)

data class TeamInfo(
    @SerializedName("teamKey") val key: String,
    @SerializedName("teamName") val name: String,
    @SerializedName("teamCode") val code: String,
    @SerializedName("teamNickname") val nickname: String,
    @SerializedName("teamTricode") val tricode: String
)
