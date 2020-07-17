package it.laface.playerlist

import it.laface.domain.model.PlayerModel

fun getPlayerListResponse(): List<PlayerModel> =
    listOf(
        PlayerModel(
            name = "firstName",
            surname = "lastName",
            id = "personId",
            teamId = "teamId",
            jerseyNumber = "jerseyNumber",
            position = "position"
        ),
        PlayerModel(
            name = "Steven",
            surname = "Adams",
            id = "203500",
            teamId = "1610612760",
            jerseyNumber = "12",
            position = "C"
        )
    )
