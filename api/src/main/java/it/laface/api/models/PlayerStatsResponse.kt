package it.laface.api.models

import com.google.gson.annotations.SerializedName

data class PlayerStatsResponse(
    @SerializedName("items") val groups: List<StatsGroup> // take only the first [index 0]
)

data class StatsGroup(
    @SerializedName("items") val sections: List<StatsSection>
)

data class StatsSection(
    @SerializedName("name") val keyName: String,
    @SerializedName("playerstats") val players: List<Player>,
    val title: String
)

data class Player(
    @SerializedName("PLAYER_ID") val playerId: Int,
    @SerializedName("PLAYER_NAME") val playerName: String,
    @SerializedName("TEAM_ID") val teamId: Int
)
