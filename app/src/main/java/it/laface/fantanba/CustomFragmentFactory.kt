package it.laface.fantanba

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import it.laface.common.navigation.Navigator
import it.laface.domain.datasource.TeamRepository
import it.laface.navigation.NavigationHandler
import it.laface.network.NbaApiMapper
import it.laface.network.NetworkManager
import it.laface.network.NewsApiMapper
import it.laface.network.TeamRepositoryImpl
import it.laface.news.NewsFragment
import it.laface.playerdetail.NavigationProvider
import it.laface.playerdetail.PlayerDetailFragment
import it.laface.playerlist.PlayerListFragment
import it.laface.ranking.RankingFragment
import it.laface.schedule.ScheduleFragment

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

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            PlayerListFragment::class.java.name -> PlayerListFragment(
                nbaApiMapper,
                NavigationProvider(navigator)
            )
            NewsFragment::class.java.name -> NewsFragment(nbaNewsApi, ActivityRegister)
            RankingFragment::class.java.name -> RankingFragment(nbaApiMapper)
            ScheduleFragment::class.java.name -> ScheduleFragment(nbaApiMapper)
            PlayerDetailFragment::class.java.name -> PlayerDetailFragment(teamRepository)
            else -> super.instantiate(classLoader, className)
        }
    }
}
