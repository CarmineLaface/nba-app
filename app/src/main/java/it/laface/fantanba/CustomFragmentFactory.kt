package it.laface.fantanba

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import it.laface.network.NbaApiMapper
import it.laface.network.NetworkManager
import it.laface.network.NewsApiMapper
import it.laface.news.NewsFragment
import it.laface.playerlist.PlayerListFragment
import it.laface.ranking.RankingFragment
import it.laface.schedule.ScheduleFragment

object CustomFragmentFactory : FragmentFactory() {

    private val nbaApi: NbaApiMapper by lazy {
        NbaApiMapper(NetworkManager.getNbaApi())
    }
    private val nbaNewsApi: NewsApiMapper by lazy {
        NewsApiMapper(NetworkManager.getNbaNewsApi())
    }

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            PlayerListFragment::class.java.name -> PlayerListFragment(nbaApi)
            NewsFragment::class.java.name -> NewsFragment(nbaNewsApi, ActivityRegister)
            RankingFragment::class.java.name -> RankingFragment(nbaApi)
            ScheduleFragment::class.java.name -> ScheduleFragment(nbaApi)
            else -> super.instantiate(classLoader, className)
        }
    }
}
