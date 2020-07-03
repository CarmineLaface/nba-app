package it.laface.playerlist

import it.laface.domain.PlayerModel
import java.io.InputStreamReader

fun Any.openResources(fileName: String): InputStreamReader =
    InputStreamReader(javaClass.classLoader!!.getResourceAsStream(fileName))

fun Any.getPlayerListResponse(): List<PlayerModel> {
    // return Gson().fromJson(openResources("players.json")", PlayerListResponse::class.java)
    return listOf(
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
}
