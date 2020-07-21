package it.laface.domain.model

import com.google.gson.annotations.SerializedName

data class NbaTeam(
    @SerializedName("teamId") val id: String,
    @SerializedName("teamKey") val key: String,
    @SerializedName("teamName") val name: String,
    @SerializedName("teamCode") val code: String,
    @SerializedName("teamNickname") val nickname: String,
    @SerializedName("teamTricode") val tricode: String
)

val NbaTeam.imageUrl: String
    get() = "https://it.global.nba.com/media/img/teams/00/logos/${tricode}_logo.png"

val NbaTeam.fullName: String
    get() = "$name $nickname"
