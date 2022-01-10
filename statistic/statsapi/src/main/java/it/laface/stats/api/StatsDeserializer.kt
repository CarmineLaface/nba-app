package it.laface.stats.api

import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import it.laface.stats.domain.Leader
import it.laface.stats.domain.StatsSection
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

    private fun JsonArray.getLeaders(keyName: String): List<Leader> {
        return mapIndexed { index, jsonElement ->
            val playerJsonObject = jsonElement.asJsonObject
            val customValue = playerJsonObject[keyName].asJsonPrimitive.asString
            Leader(
                playerId = playerJsonObject["PLAYER_ID"].asInt.toString(),
                playerName = playerJsonObject["PLAYER_NAME"].asString,
                teamId = playerJsonObject["TEAM_ID"].asInt.toString(),
                rank = index + 1,
                customValue = customValue.parseCustomValue()
            )
        }
    }

    @Suppress("MagicNumber")
    private fun String.parseCustomValue(): String {
        if (contains('.').not()) return this
        val pointIndex = indexOf('.')
        return if (startsWith("0.")) {
            safeSubstring(1, pointIndex + 4)
        } else {
            safeSubstring(0, pointIndex + 3)
        }
    }

    private fun String.safeSubstring(startIndex: Int, endIndex: Int): String =
        if (endIndex > length) {
            substring(startIndex)
        } else {
            substring(startIndex, endIndex)
        }
}
