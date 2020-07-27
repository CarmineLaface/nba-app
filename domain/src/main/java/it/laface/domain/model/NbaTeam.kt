package it.laface.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NbaTeam(
    @SerializedName("teamId") val id: String,
    @SerializedName("teamKey") val key: String,
    @SerializedName("teamName") val cityName: String,
    @SerializedName("teamCode") val code: String,
    @SerializedName("teamNickname") val nickname: String,
    @SerializedName("teamTricode") val tricode: String,
    @SerializedName("primary") val rgbColor: String? = null
) : Parcelable

val NbaTeam.imageUrl: String
    get() = "https://it.global.nba.com/media/img/teams/00/logos/${tricode}_logo.png"

val NbaTeam.fullName: String
    get() = "$cityName $nickname"
