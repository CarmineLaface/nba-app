package it.laface.fantanba

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import it.laface.fantanba.R.id
import it.laface.game.networking.GameApi
import it.laface.game.networking.GameMapper
import it.laface.game.presentation.GameFragment
import it.laface.game.presentation.GamePageProviderImpl
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
import it.laface.ranking.networking.RankingApi
import it.laface.ranking.networking.RankingMapper
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
import it.laface.team.presentation.TeamDataSourcesManager
import it.laface.team.presentation.TeamFragment
import it.laface.team.presentation.TeamPageProviderImpl

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
                NewsFragment(
                    NewsMapper(NewsApi.service),
                    BrowserProviderImpl(ActivityRegister)
                )
            RankingFragment::class.java.name ->
                RankingFragment(
                    dataSource = RankingMapper(RankingApi.service),
                    teamPageProvider = TeamPageProviderImpl,
                    navigator = navigator
                )
            ScheduleFragment::class.java.name ->
                ScheduleFragment(
                    dataSource = ScheduleMapper(ScheduleApi.service, teamRepository),
                    navigator = navigator,
                    gamePageProvider = GamePageProviderImpl
                )
            PlayerDetailFragment::class.java.name ->
                PlayerDetailFragment(
                    teamRepository = teamRepository,
                    playerStatsDataSource = PlayerStatsMapper(PlayerApi.service),
                    navigator = navigator,
                    teamPageProvider = TeamPageProviderImpl
                )
            TeamFragment::class.java.name -> {
                val teamDataSourcesManager = TeamDataSourcesManager(
                    TeamRosterMapper(TeamApi.teamRosterService),
                    ScheduleMapper(ScheduleApi.service, teamRepository),
                    TeamInfoMapper(TeamApi.teamDetailsService)
                )
                TeamFragment(
                    teamDataSourcesManager = teamDataSourcesManager,
                    navigator = navigator,
                    playerPageProvider = PlayerPageProvider,
                    gamePageProvider = GamePageProviderImpl
                )
            }
            StatsFragment::class.java.name ->
                StatsFragment(
                    navigator = navigator,
                    leadersPageProvider = LeadersPageProviderImpl,
                    statsDataSource = StatsMapper(StatsApi.service)
                )
            GameFragment::class.java.name ->
                GameFragment(
                    GameMapper(GameApi.service),
                    navigator
                )
            LeadersFragment::class.java.name ->
                LeadersFragment(navigator)
            else -> super.instantiate(classLoader, className)
        }
    }
}
