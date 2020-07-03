package it.laface.api.models

class TeamConfigurationResponse(
    val teams: Teams
)

class Teams(
    val config: List<Team>
)

class Team(
    val primaryColor: String,
    val secondaryColor: String,
    val tricode: String,
    val ttsName: String?,
    val teamId: String
)
