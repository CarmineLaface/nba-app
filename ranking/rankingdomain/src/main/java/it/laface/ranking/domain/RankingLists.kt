package it.laface.ranking.domain

import it.laface.domain.model.Team

data class RankedTeam(
    val rankingPosition: String,
    val wins: String,
    val losses: String,
    val teamInfo: Team
)

data class RankingLists(
    val westCoastRanking: List<RankedTeam>,
    val eastCoastRanking: List<RankedTeam>
)
