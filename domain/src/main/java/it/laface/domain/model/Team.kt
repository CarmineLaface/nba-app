package it.laface.domain.model

import java.io.Serializable

data class Team(
    val id: String,
    val key: String,
    val cityName: String,
    val code: String,
    val nickname: String,
    val tricode: String,
    val rgbColor: String? = null
) : Serializable {

    companion object {
        const val serialVersionUID = 127L
    }
}

val Team.imageUrl: String
    get() = "https://it.global.nba.com/media/img/teams/00/logos/${tricode}_logo.png"

val Team.fullName: String
    get() = "$cityName $nickname"
