package it.laface.network

import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import it.laface.api.models.Player
import it.laface.api.models.PlayerStatsResponse
import it.laface.api.models.StatsSection
import java.lang.reflect.Type

object StatisticDeserializer : JsonDeserializer<PlayerStatsResponse> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): PlayerStatsResponse {
        val jsonArray =
            json.asJsonObject["items"].asJsonArray.get(0).asJsonObject["items"].asJsonArray
        return PlayerStatsResponse(jsonArray.getStatsSections())
    }

    private fun JsonArray.getStatsSections(): List<StatsSection> {
        return map { it.asJsonObject }
            .filter {
                it.has("playerstats")
            }
            .map { jsonObject ->
                val keyName = jsonObject["name"].asString
                val players = jsonObject["playerstats"].asJsonArray.getLeaders(keyName)
                val title = jsonObject["title"].asString
                StatsSection(
                    title = title,
                    players = players
                )
            }
    }

    private fun JsonArray.getLeaders(keyName: String): List<Player> {
        return map { jsonElement ->
            val playerJsonObject = jsonElement.asJsonObject
            Player(
                playerId = playerJsonObject["PLAYER_ID"].asInt,
                playerName = playerJsonObject["PLAYER_NAME"].asString,
                teamId = playerJsonObject["TEAM_ID"].asInt,
                value = playerJsonObject[keyName].asJsonPrimitive.asString
            )
        }
    }
}
