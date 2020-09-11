package it.laface.team.api.teaminfo

import it.laface.base.NetworkResult
import it.laface.networking.toNetworkResult
import it.laface.team.domain.AccountType
import it.laface.team.domain.Championship
import it.laface.team.domain.SocialPage
import it.laface.team.domain.TeamInfo
import it.laface.team.domain.TeamInfoDataSource

class TeamInfoMapper(private val service: TeamDetailsService) : TeamInfoDataSource {

    override suspend fun getTeamInfo(teamId: String): NetworkResult<TeamInfo> {
        return service.getTeamDetails(teamId).toNetworkResult { response ->
            val details = response.details
            TeamInfo(
                yearFounded = details.yearFounded,
                city = details.city,
                arena = details.arena,
                arenaCapacity = details.arenaCapacity,
                owner = details.owner,
                generalManager = details.generalManager,
                headCoach = details.headCoach,
                socialPages = response.socialSites.toDomain(),
                championships = response.championships.map(::toDomain),
            )
        }
    }

    private fun List<SocialSite>.toDomain(): List<SocialPage> {
        val accountTypesMap = mapOf(
            "Facebook" to AccountType.Facebook,
            "Instagram" to AccountType.Facebook,
            "Twitter" to AccountType.Twitter
        )
        return filter { accountTypesMap.keys.contains(it.accountType) }
            .map {
                SocialPage(
                    type = accountTypesMap.getValue(it.accountType),
                    webUrl = it.websiteLink
                )
            }
    }

    private fun toDomain(championship: ChampionshipTitle): Championship =
        Championship(championship.yearAwarded.toString())
}
