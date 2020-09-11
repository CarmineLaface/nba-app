package it.laface.team.domain

data class TeamInfo(
    val yearFounded: Int,
    val city: String,
    val arena: String,
    val arenaCapacity: String,
    val owner: String,
    val generalManager: String,
    val headCoach: String,
    val socialPages: List<SocialPage>,
    val championships: List<Championship>
)

data class Championship(val year: String)

data class SocialPage(
    val type: AccountType,
    val webUrl: String
)

enum class AccountType {
    Facebook, Instagram, Twitter
}
