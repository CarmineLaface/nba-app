package it.laface.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
    val name: String,
    val surname: String,
    val id: String,
    val teamId: String,
    val jerseyNumber: String,
    val position: String
) : Parcelable

val Player.imageUrl: String
    get() = getPlayerImageUrl(id)

val Player.fullName: String
    get() = "$name $surname"

fun getPlayerImageUrl(playerId: String): String =
    "https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/$playerId.png"
