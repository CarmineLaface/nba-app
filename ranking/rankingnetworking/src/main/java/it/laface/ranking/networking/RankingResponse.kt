package it.laface.ranking.networking

import com.google.gson.annotations.SerializedName

class RankingResponse(
    @SerializedName("league") val league: League
)

class League(
    @SerializedName("standard") val standard: Standard
)

class Standard(
    @SerializedName("conference") val conference: Conference
)

class Conference(
    @SerializedName("east") val east: List<TeamResponseModel>,
    @SerializedName("west") val west: List<TeamResponseModel>
)

class TeamResponseModel(
    @SerializedName("teamId") val id: String,
    @SerializedName("win") val win: String,
    @SerializedName("loss") val loss: String,
    @SerializedName("confRank") val rankingPosition: String,
    @SerializedName("teamSitesOnly") val info: TeamInfo
)

class TeamInfo(
    @SerializedName("teamKey") val key: String,
    @SerializedName("teamName") val name: String,
    @SerializedName("teamCode") val code: String,
    @SerializedName("teamNickname") val nickname: String,
    @SerializedName("teamTricode") val tricode: String
)
