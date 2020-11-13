package it.laface.ranking.networking

import it.laface.base.NetworkResult
import it.laface.domain.model.Team
import it.laface.networking.toNetworkResult
import it.laface.ranking.domain.RankedTeam
import it.laface.ranking.domain.RankingDataSource
import it.laface.ranking.domain.RankingLists

class RankingMapper(private val service: RankingService) : RankingDataSource {

    override suspend fun getRanking(): NetworkResult<RankingLists> =
        service.ranking().toNetworkResult { response ->
            val conference = response.league.standard.conference
            val westCoastRanking = conference.west.map(::toDomain)
            val eastCoastRanking = conference.east.map(::toDomain)
            RankingLists(
                westCoastRanking = westCoastRanking,
                eastCoastRanking = eastCoastRanking
            )
        }

    private fun toDomain(team: TeamResponseModel) = RankedTeam(
        rankingPosition = team.rankingPosition.toIntString,
        wins = team.win.toIntString,
        losses = team.loss.toIntString,
        teamInfo = Team(
            id = team.id,
            key = team.info.key,
            code = team.info.code,
            cityName = team.info.name,
            tricode = team.info.tricode,
            nickname = team.info.nickname
        )
    )

    private val String.toIntString: String
        get() = if (length == 1) "0$this" else this
}
