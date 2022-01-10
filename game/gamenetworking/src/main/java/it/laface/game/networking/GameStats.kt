package it.laface.game.networking

import com.google.gson.annotations.SerializedName

class GameStats(
    @SerializedName("vTeam") val visitorTeam: TeamStatsResponse,
    @SerializedName("hTeam") val homeTeam: TeamStatsResponse,
)

class TeamStatsResponse(
    @SerializedName("totals") val totals: TotalStats,
    @SerializedName("leaders") val leaders: Leaders,
)

@Suppress("LongParameterList")
class TotalStats(
    @SerializedName("fgm") val fieldGoalsMade: String,
    @SerializedName("fga") val fieldGoalsAttempted: String,
    @SerializedName("fgp") val fieldGoalPercentage: String,
    @SerializedName("ftm") val freeThrowsMade: String,
    @SerializedName("fta") val freeThrowsAttempted: String,
    @SerializedName("ftp") val freeThrowsPercentage: String,
    @SerializedName("tpm") val threePointMade: String,
    @SerializedName("tpa") val threePointAttempted: String,
    @SerializedName("tpp") val threePointPercentage: String,
    @SerializedName("offReb") val offensiveRebounds: String,
    @SerializedName("defReb") val defensiveRebounds: String,
    @SerializedName("totReb") val totalRebounds: String,
    @SerializedName("assists") val assists: String,
    @SerializedName("steals") val steals: String,
    @SerializedName("turnovers") val turnovers: String,
    @SerializedName("blocks") val blocks: String,
)

class Leaders(
    @SerializedName("points") val points: TeamLeadersResponse,
    @SerializedName("rebounds") val rebounds: TeamLeadersResponse,
    @SerializedName("assists") val assists: TeamLeadersResponse,
)

class TeamLeadersResponse(
    @SerializedName("value") val value: String,
    @SerializedName("players") val players: List<PlayerResponse>,
)

class PlayerResponse(
    @SerializedName("personId") val id: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
)
