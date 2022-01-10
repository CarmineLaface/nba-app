package it.laface.game.networking

import com.google.gson.annotations.SerializedName

class GameResponse(
    @SerializedName("basicGameData") val basicGameData: GameData,
    @SerializedName("stats") val gameStats: GameStats,
)
