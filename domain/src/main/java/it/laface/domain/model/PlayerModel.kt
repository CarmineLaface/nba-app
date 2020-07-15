package it.laface.domain.model

data class PlayerModel(
    val name: String,
    val surname: String,
    val id: String,
    val teamId: String,
    val jerseyNumber: String,
    val position: String
)

val PlayerModel.imageUrl: String
    get() = "https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/$id.png"

val PlayerModel.fullName: String
    get() = "$name $surname"
