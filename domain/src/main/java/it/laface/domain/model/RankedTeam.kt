package it.laface.domain.model

data class RankedTeam(
    val rankingPosition: String,
    val teamInfo: NbaTeam
)

data class RankingLists(
    val westCoastRanking: List<RankedTeam>,
    val eastCoastRanking: List<RankedTeam>
)
