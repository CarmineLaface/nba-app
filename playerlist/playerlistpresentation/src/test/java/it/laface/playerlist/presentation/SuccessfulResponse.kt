package it.laface.playerlist.presentation

import it.laface.base.NetworkResult
import it.laface.player.domain.Player

internal val successFulResponse: NetworkResult<List<Player>> =
    NetworkResult.Success(
        listOf(
            Player(
                name = "Precious",
                surname = "Achiuwa",
                id = "1630173",
                teamId = "1610612748",
                jerseyNumber = "5",
                position = "F"
            ),
            Player(
                name = "Jaylen",
                surname = "Adams",
                id = "1629121",
                teamId = "1610612749",
                jerseyNumber = "6",
                position = "G"
            ),
            Player(
                name = "Steven",
                surname = "Adams",
                id = "203500",
                teamId = "1610612740",
                jerseyNumber = "12",
                position = "C"
            ),
            Player(
                name = "Bam",
                surname = "Adebayo",
                id = "1628389",
                teamId = "1610612748",
                jerseyNumber = "13",
                position = "C-F"
            ),
            Player(
                name = "LaMarcus",
                surname = "Aldridge",
                id = "200746",
                teamId = "1610612759",
                jerseyNumber = "12",
                position = "C-F"
            ),
            Player(
                name = "Ty-Shon",
                surname = "Alexander",
                id = "1630234",
                teamId = "1610612756",
                jerseyNumber = "0",
                position = "G"
            ),
            Player(
                name = "Nickeil",
                surname = "Alexander-Walker",
                id = "1629638",
                teamId = "1610612740",
                jerseyNumber = "6",
                position = "G"
            ),
            Player(
                name = "Grayson",
                surname = "Allen",
                id = "1628960",
                teamId = "1610612763",
                jerseyNumber = "3",
                position = "G"
            ),
        )
    )
