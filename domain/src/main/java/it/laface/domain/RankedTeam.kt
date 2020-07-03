package it.laface.domain

data class RankedTeam(
    val rankingPosition: String,
    val id: String,
    val code: String,
    val name: String
)

data class RankingLists(
    val westCoastRanking: List<RankedTeam>,
    val eastCoastRanking: List<RankedTeam>
)

val RankedTeam.imageUrl: String
    get() = "https://it.global.nba.com/media/img/teams/00/logos/${code}_logo.png"
