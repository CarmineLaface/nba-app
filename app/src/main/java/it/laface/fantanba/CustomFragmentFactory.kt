package it.laface.fantanba

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import it.laface.news.BrowserProviderImpl
import it.laface.domain.datasource.TeamRepository
import it.laface.domain.navigation.Navigator
import it.laface.navigation.NavigationHandler
import it.laface.network.NbaApiMapper
import it.laface.network.NbaStatsMapper
import it.laface.network.NetworkManager
import it.laface.network.NewsApiMapper
import it.laface.network.TeamRepositoryImpl
import it.laface.news.NewsFragment
import it.laface.playerdetail.PlayerDetailFragment
import it.laface.playerdetail.PlayerPageProvider
import it.laface.playerlist.PlayerListFragment
import it.laface.ranking.RankingFragment
import it.laface.schedule.ScheduleFragment
import it.laface.statistics.StatsFragment
import it.laface.statistics.StatsPageProviderImpl
import it.laface.statistics.detail.LeadersFragment
import it.laface.statistics.detail.LeadersPageProviderImpl
import it.laface.team.TeamFragment
import it.laface.team.TeamPageProviderImpl

object CustomFragmentFactory : FragmentFactory() {

    private val navigator: Navigator by lazy {
        NavigationHandler(ActivityRegister, R.id.container)
    }
    private val teamRepository: TeamRepository by lazy(::TeamRepositoryImpl)
    private val nbaApiMapper: NbaApiMapper by lazy {
        NbaApiMapper(NetworkManager.getNbaApi(), teamRepository)
    }
    private val nbaNewsApi: NewsApiMapper by lazy {
        NewsApiMapper(NetworkManager.getNbaNewsApi())
    }
    private val nbaStatsApi: NbaStatsMapper by lazy {
        NbaStatsMapper(NetworkManager.getStatsApi())
    }

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            PlayerListFragment::class.java.name ->
                PlayerListFragment(nbaApiMapper, PlayerPageProvider, navigator, StatsPageProviderImpl)
            NewsFragment::class.java.name ->
                NewsFragment(nbaNewsApi, BrowserProviderImpl(ActivityRegister))
            RankingFragment::class.java.name ->
                RankingFragment(nbaApiMapper, TeamPageProviderImpl, navigator)
            ScheduleFragment::class.java.name ->
                ScheduleFragment(nbaApiMapper)
            PlayerDetailFragment::class.java.name ->
                PlayerDetailFragment(teamRepository, navigator)
            TeamFragment::class.java.name ->
                TeamFragment(nbaApiMapper, nbaApiMapper, navigator, PlayerPageProvider)
            StatsFragment::class.java.name ->
                StatsFragment(navigator, LeadersPageProviderImpl, nbaStatsApi)
            LeadersFragment::class.java.name ->
                LeadersFragment(navigator)
            else -> super.instantiate(classLoader, className)
        }
    }
}
