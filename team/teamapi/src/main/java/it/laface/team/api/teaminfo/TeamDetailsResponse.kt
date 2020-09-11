package it.laface.team.api.teaminfo

import com.google.gson.annotations.SerializedName

data class TeamDetailsResponse(
    val details: TeamDetails,
    val socialSites: List<SocialSite>,
    val championships: List<ChampionshipTitle>
)

data class TeamDetails(
    @SerializedName("YearFounded") val yearFounded: Int,
    @SerializedName("City") val city: String,
    @SerializedName("Arena") val arena: String,
    @SerializedName("ArenaCapacity") val arenaCapacity: String,
    @SerializedName("Owner") val owner: String,
    @SerializedName("GeneralManager") val generalManager: String,
    @SerializedName("HeadCoach") val headCoach: String,
)

data class SocialSite(
    @SerializedName("AccountType") val accountType: String,
    @SerializedName("WebSite_Link") val websiteLink: String,
)

data class ChampionshipTitle(
    @SerializedName("YearAwarded") val yearAwarded: Int,
    @SerializedName("OppositeTeam") val oppositeTeam: String
)
