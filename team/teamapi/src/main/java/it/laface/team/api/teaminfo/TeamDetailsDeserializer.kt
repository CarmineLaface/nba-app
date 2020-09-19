package it.laface.team.api.teaminfo

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

object TeamDetailsDeserializer : JsonDeserializer<TeamDetailsResponse> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): TeamDetailsResponse {
        val gson = Gson()
        val teamDetailsJson: JsonArray = json.asJsonObject["TeamDetails"].asJsonArray
        val detailsJson: JsonElement =
            teamDetailsJson.get(0).asJsonObject["Details"].asJsonArray.get(0)
        val teamDetails: TeamDetails = gson.fromJson(detailsJson, TeamDetails::class.java)
        val socialSitesJson: JsonArray =
            teamDetailsJson.get(SOCIAL_SITES_INDEX).asJsonObject["SocialSites"].asJsonArray
        val socialSites: List<SocialSite> = socialSitesJson.map { socialSiteJson ->
            gson.fromJson(socialSiteJson, SocialSite::class.java)
        }
        val awardsJson: JsonArray =
            teamDetailsJson.get(AWARDS_INDEX).asJsonObject["Awards"].asJsonArray
        val championshipsJson: JsonArray =
            awardsJson.get(0).asJsonObject["Championships"].asJsonArray
        val championships: List<ChampionshipTitle> = championshipsJson.map { championshipJson ->
            gson.fromJson(championshipJson, ChampionshipTitle::class.java)
        }
        return TeamDetailsResponse(
            details = teamDetails,
            socialSites = socialSites,
            championships = championships,
        )
    }

    const val SOCIAL_SITES_INDEX = 2
    const val AWARDS_INDEX = 3
}
