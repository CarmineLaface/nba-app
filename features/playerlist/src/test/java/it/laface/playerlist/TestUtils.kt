package it.laface.playerlist

import it.laface.domain.model.Player

fun getPlayerListResponse(): List<Player> =
    listOf(
        Player(
            name = "firstName",
            surname = "lastName",
            id = "personId",
            teamId = "teamId",
            jerseyNumber = "jerseyNumber",
            position = "position"
        ),
        Player(
            name = "Steven",
            surname = "Adams",
            id = "203500",
            teamId = "1610612760",
            jerseyNumber = "12",
            position = "C"
        )
    )
