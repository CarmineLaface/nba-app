package it.laface.api.models

import com.google.gson.annotations.SerializedName

data class RankingResponse(
    @SerializedName("league") val league: League
)

data class League(
    @SerializedName("standard") val standard: Standard
)

data class Standard(
    @SerializedName("conference") val conference: Conference
)

data class Conference(
    @SerializedName("east") val east: List<TeamResponseModel>,
    @SerializedName("west") val west: List<TeamResponseModel>
)

data class TeamResponseModel(
    @SerializedName("teamId") val id: String,
    @SerializedName("win") val win: String,
    @SerializedName("loss") val loss: String,
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
