package it.laface.fantanba

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import it.laface.game.networking.GameApi
import it.laface.game.networking.GameMapper
import it.laface.game.presentation.GameFragment
import it.laface.game.presentation.gamePageProvider
import it.laface.navigation.NavigationHandler
import it.laface.navigation.Navigator
import it.laface.navigation.SnackbarHandler
import it.laface.news.api.NewsApi
import it.laface.news.api.NewsMapper
import it.laface.news.presentation.BrowserProviderImpl
import it.laface.news.presentation.NewsFragment
import it.laface.player.api.PlayerApi
import it.laface.player.api.PlayerStatsMapper
import it.laface.player.presentation.PlayerDetailFragment
import it.laface.player.presentation.playerPageProvider
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
import it.laface.stats.presentation.detail.leadersPageProvider
import it.laface.stats.presentation.group.StatsFragment
import it.laface.stats.presentation.group.statsPageProvider
import it.laface.team.api.TeamApi
import it.laface.team.api.TeamRepositoryImpl
import it.laface.team.api.roster.TeamRosterMapper
import it.laface.team.api.teaminfo.TeamInfoMapper
import it.laface.team.domain.TeamRepository
import it.laface.team.presentation.TeamDataSourcesManager
import it.laface.team.presentation.TeamFragment
import it.laface.team.presentation.teamPageProvider

object CustomFragmentFactory : FragmentFactory() {

    internal val navigator: Navigator by lazy {
        NavigationHandler(ActivityRegister, R.id.container)
    }
    private val teamRepository: TeamRepository by lazy(::TeamRepositoryImpl)
    private val snackbarHandler: SnackbarHandler by lazy {
        SnackbarHandler(ActivityRegister, R.id.bottomNavigationView)
    }
    private val teamDataSourcesManager: TeamDataSourcesManager
        get() = TeamDataSourcesManager(
            rosterDataSource = TeamRosterMapper(TeamApi.teamRosterService),
            scheduleDataSource = ScheduleMapper(ScheduleApi.service, teamRepository),
            teamInfoDataSource = TeamInfoMapper(TeamApi.teamDetailsService),
        )

    @Suppress("LongMethod")
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            PlayerListFragment::class.java.name ->
                PlayerListFragment(
                    dataSource = PlayerListMapper(PlayerListApi.service),
                    playerDetailPageProvider = playerPageProvider,
                    navigator = navigator,
                    statsPageProvider = statsPageProvider,
                )
            NewsFragment::class.java.name ->
                NewsFragment(
                    dataSource = NewsMapper(NewsApi.service),
                    browserProvider = BrowserProviderImpl(ActivityRegister),
                    messageEmitter = snackbarHandler,
                )
            RankingFragment::class.java.name ->
                RankingFragment(
                    dataSource = RankingMapper(RankingApi.service),
                    teamPageProvider = teamPageProvider,
                    navigator = navigator,
                )
            ScheduleFragment::class.java.name ->
                ScheduleFragment(
                    dataSource = ScheduleMapper(ScheduleApi.service, teamRepository),
                    navigator = navigator,
                    gamePageProvider = gamePageProvider,
                )
            PlayerDetailFragment::class.java.name ->
                PlayerDetailFragment(
                    teamRepository = teamRepository,
                    playerStatsDataSource = PlayerStatsMapper(PlayerApi.service),
                    navigator = navigator,
                    teamPageProvider = teamPageProvider,
                )
            TeamFragment::class.java.name ->
                TeamFragment(
                    teamDataSourcesManager = teamDataSourcesManager,
                    navigator = navigator,
                    playerPageProvider = playerPageProvider,
                    gamePageProvider = gamePageProvider,
                )
            StatsFragment::class.java.name ->
                StatsFragment(
                    navigator = navigator,
                    leadersPageProvider = leadersPageProvider,
                    statsDataSource = StatsMapper(StatsApi.service),
                )
            GameFragment::class.java.name ->
                GameFragment(
                    GameMapper(GameApi.service),
                    navigator,
                    teamPageProvider,
                )
            LeadersFragment::class.java.name ->
                LeadersFragment(navigator)
            else -> super.instantiate(classLoader, className)
        }
    }
}
