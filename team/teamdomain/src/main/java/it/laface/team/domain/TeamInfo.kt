package it.laface.team.domain

data class TeamInfo(
    val city: String,
    val yearFounded: String,
    val arena: String,
    val owner: String,
    val generalManager: String,
    val headCoach: String,
    val socialPages: List<SocialPage>,
    val championships: List<Championship>
)

data class Championship(val year: String)

data class SocialPage(
    val accountType: String,
    val webUrl: String
)
