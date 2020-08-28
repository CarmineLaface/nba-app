package it.laface.stats.api

import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

object StatsDeserializer : JsonDeserializer<PlayerStatsResponse> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): PlayerStatsResponse {
        val jsonArray =
            json.asJsonObject["items"].asJsonArray.get(0).asJsonObject["items"].asJsonArray
        return PlayerStatsResponse(jsonArray.getStatsSections())
    }

    private fun JsonArray.getStatsSections(): List<StatsSectionResponse> {
        return map { it.asJsonObject }
            .filter {
                it.has("playerstats")
            }
            .map { jsonObject ->
                val keyName = jsonObject["name"].asString
                val players = jsonObject["playerstats"].asJsonArray.getLeaders(keyName)
                val title = jsonObject["title"].asString
                StatsSectionResponse(
                    title = title,
                    players = players
                )
            }
    }

    private fun JsonArray.getLeaders(keyName: String): List<PlayerResponse> {
        return map { jsonElement ->
            val playerJsonObject = jsonElement.asJsonObject
            val customValue = playerJsonObject[keyName].asJsonPrimitive.asString
            PlayerResponse(
                playerId = playerJsonObject["PLAYER_ID"].asInt,
                playerName = playerJsonObject["PLAYER_NAME"].asString,
                teamId = playerJsonObject["TEAM_ID"].asInt,
                value = customValue
            )
        }
    }
}
