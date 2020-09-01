package it.laface.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val id: String,
    val key: String,
    val cityName: String,
    val code: String,
    val nickname: String,
    val tricode: String,
    val rgbColor: String? = null
) : Parcelable

val Team.imageUrl: String
    get() = "https://it.global.nba.com/media/img/teams/00/logos/${tricode}_logo.png"

val Team.fullName: String
    get() = "$cityName $nickname"
