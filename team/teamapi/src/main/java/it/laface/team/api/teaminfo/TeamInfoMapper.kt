package it.laface.team.api.teaminfo

import it.laface.base.NetworkResult
import it.laface.networking.toNetworkResult
import it.laface.team.domain.Championship
import it.laface.team.domain.SocialPage
import it.laface.team.domain.TeamInfo
import it.laface.team.domain.TeamInfoDataSource

class TeamInfoMapper(private val service: TeamDetailsService) : TeamInfoDataSource {

    override suspend fun getTeamInfo(teamId: String): NetworkResult<TeamInfo> {
        return service.getTeamDetails(teamId).toNetworkResult { response ->
            val details = response.details
            TeamInfo(
                yearFounded = details.yearFounded.toString(),
                city = details.city,
                arena = details.arena,
                owner = details.owner,
                generalManager = details.generalManager,
                headCoach = details.headCoach,
                socialPages = response.socialSites.map(::toDomain),
                championships = response.championships.map(::toDomain),
            )
        }
    }

    private fun toDomain(socialSite: SocialSite): SocialPage =
        SocialPage(
            accountType = socialSite.accountType,
            webUrl = socialSite.websiteLink
        )

    private fun toDomain(championship: ChampionshipTitle): Championship =
        Championship(championship.yearAwarded.toString())
}
