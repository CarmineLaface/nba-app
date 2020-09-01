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
import it.laface.playerdetail.PlayerDetailFragment
import it.laface.playerdetail.PlayerPageProvider
import it.laface.playerlist.PlayerListFragment
import it.laface.playerlist.api.PlayerListApi
import it.laface.playerlist.api.PlayerListMapper
import it.laface.ranking.RankingFragment
import it.laface.ranking.api.RankingApi
import it.laface.ranking.api.RankingMapper
import it.laface.schedule.ScheduleFragment
import it.laface.schedule.api.ScheduleApi
import it.laface.schedule.api.ScheduleMapper
import it.laface.statistics.detail.LeadersFragment
import it.laface.statistics.detail.LeadersPageProviderImpl
import it.laface.statistics.group.StatsFragment
import it.laface.statistics.group.StatsPageProviderImpl
import it.laface.stats.api.StatsApi
import it.laface.stats.api.StatsMapper
import it.laface.team.TeamFragment
import it.laface.team.TeamPageProviderImpl
import it.laface.team.api.TeamRepositoryImpl
import it.laface.team.api.TeamRosterApi
import it.laface.team.api.TeamRosterMapper
import it.laface.team.domain.TeamRepository

object CustomFragmentFactory : FragmentFactory() {

    private val navigator: Navigator by lazy {
        NavigationHandler(ActivityRegister, id.container)
    }
    private val teamRepository: TeamRepository by lazy(::TeamRepositoryImpl)

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            PlayerListFragment::class.java.name ->
                PlayerListFragment(
                    dataSource = PlayerListMapper(PlayerListApi.service),
                    playerDetailPageProvider = PlayerPageProvider,
                    navigator = navigator,
                    statsPageProvider = StatsPageProviderImpl
                )
            NewsFragment::class.java.name ->
                NewsFragment(NewsMapper(NewsApi.service), BrowserProviderImpl(ActivityRegister))
            RankingFragment::class.java.name ->
                RankingFragment(RankingMapper(RankingApi.service), TeamPageProviderImpl, navigator)
            ScheduleFragment::class.java.name ->
                ScheduleFragment(ScheduleMapper(ScheduleApi.service, teamRepository))
            PlayerDetailFragment::class.java.name ->
                PlayerDetailFragment(teamRepository, navigator, TeamPageProviderImpl)
            TeamFragment::class.java.name ->
                TeamFragment(
                    rosterDataSource = TeamRosterMapper(TeamRosterApi.service),
                    scheduleDataSource = ScheduleMapper(ScheduleApi.service, teamRepository),
                    navigator = navigator,
                    playerPageProvider = PlayerPageProvider
                )
            StatsFragment::class.java.name ->
                StatsFragment(navigator, LeadersPageProviderImpl, StatsMapper(StatsApi.service))
            LeadersFragment::class.java.name ->
                LeadersFragment(navigator)
            else -> super.instantiate(classLoader, className)
        }
    }
}
