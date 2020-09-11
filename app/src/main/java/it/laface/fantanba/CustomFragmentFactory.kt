package it.laface.fantanba

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import it.laface.fantanba.R.id
import it.laface.navigation.NavigationHandler
import it.laface.navigation.Navigator
import it.laface.news.api.NewsApi
import it.laface.news.api.NewsMapper
import it.laface.news.presentation.BrowserProviderImpl
import it.laface.news.presentation.NewsFragment
import it.laface.player.api.PlayerApi
import it.laface.player.api.PlayerStatsMapper
import it.laface.player.presentation.PlayerDetailFragment
import it.laface.player.presentation.PlayerPageProvider
import it.laface.playerlist.api.PlayerListApi
import it.laface.playerlist.api.PlayerListMapper
import it.laface.playerlist.presentation.PlayerListFragment
import it.laface.ranking.api.RankingApi
import it.laface.ranking.api.RankingMapper
import it.laface.ranking.presentation.RankingFragment
import it.laface.schedule.api.ScheduleApi
import it.laface.schedule.api.ScheduleMapper
import it.laface.schedule.presentation.ScheduleFragment
import it.laface.stats.api.StatsApi
import it.laface.stats.api.StatsMapper
import it.laface.stats.presentation.detail.LeadersFragment
import it.laface.stats.presentation.detail.LeadersPageProviderImpl
import it.laface.stats.presentation.group.StatsFragment
import it.laface.stats.presentation.group.StatsPageProviderImpl
import it.laface.team.api.TeamApi
import it.laface.team.api.TeamRepositoryImpl
import it.laface.team.api.roster.TeamRosterMapper
import it.laface.team.api.teaminfo.TeamInfoMapper
import it.laface.team.domain.TeamRepository
import it.laface.team.presentation.TeamFragment
import it.laface.team.presentation.TeamPageProviderImpl

class CustomFragmentFactory(private val cacheDirPath: String) : FragmentFactory() {

    private val navigator: Navigator by lazy {
        NavigationHandler(ActivityRegister, id.container)
    }
    private val teamRepository: TeamRepository by lazy(::TeamRepositoryImpl)

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            PlayerListFragment::class.java.name ->
                PlayerListFragment(
                    dataSource = PlayerListMapper(PlayerListApi.getService(cacheDirPath)),
                    playerDetailPageProvider = PlayerPageProvider,
                    navigator = navigator,
                    statsPageProvider = StatsPageProviderImpl
                )
            NewsFragment::class.java.name ->
                NewsFragment(NewsMapper(NewsApi.service), BrowserProviderImpl(ActivityRegister))
            RankingFragment::class.java.name ->
                RankingFragment(
                    RankingMapper(RankingApi.service),
                    TeamPageProviderImpl,
                    navigator
                )
            ScheduleFragment::class.java.name ->
                ScheduleFragment(
                    ScheduleMapper(ScheduleApi.service, teamRepository)
                )
            PlayerDetailFragment::class.java.name ->
                PlayerDetailFragment(
                    teamRepository = teamRepository,
                    playerStatsDataSource = PlayerStatsMapper(PlayerApi.service),
                    navigator = navigator,
                    teamPageProvider = TeamPageProviderImpl
                )
            TeamFragment::class.java.name ->
                TeamFragment(
                    rosterDataSource = TeamRosterMapper(TeamApi.teamRosterService),
                    scheduleDataSource = ScheduleMapper(ScheduleApi.service, teamRepository),
                    teamInfoDataSource = TeamInfoMapper(TeamApi.teamDetailsService),
                    navigator = navigator,
                    playerPageProvider = PlayerPageProvider
                )
            StatsFragment::class.java.name ->
                StatsFragment(
                    navigator,
                    LeadersPageProviderImpl,
                    StatsMapper(StatsApi.service)
                )
            LeadersFragment::class.java.name ->
                LeadersFragment(navigator)
            else -> super.instantiate(classLoader, className)
        }
    }
}
