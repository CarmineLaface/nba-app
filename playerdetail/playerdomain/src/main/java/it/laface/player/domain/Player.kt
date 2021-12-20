package it.laface.player.domain

import it.laface.domain.model.getPlayerImageUrl
import java.io.Serializable

data class Player(
    val name: String,
    val surname: String,
    val id: String,
    val teamId: String,
    val jerseyNumber: String,
    val position: String
) : Serializable {

    companion object {
        const val serialVersionUID = 125L
    }
}

val Player.imageUrl: String
    get() = getPlayerImageUrl(id)

val Player.fullName: String
    get() = "$name $surname"
