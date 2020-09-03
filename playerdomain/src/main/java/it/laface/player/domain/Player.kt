package it.laface.player.domain

import android.os.Parcelable
import it.laface.domain.model.getPlayerImageUrl
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
