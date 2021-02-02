package it.laface.fantanba

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import it.laface.news.presentation.NewsFragment
import it.laface.playerlist.presentation.PlayerListFragment
import it.laface.ranking.presentation.RankingFragment
import it.laface.schedule.presentation.ScheduleFragment

@Suppress("UNCHECKED_CAST")
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val firstFragmentClass: Class<Fragment>
        get() = NewsFragment::class.java as Class<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = CustomFragmentFactory
        super.onCreate(savedInstanceState)

        setBottomNavigation()

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.container, firstFragmentClass, null, firstFragmentClass.name)
            }
        }
    }

    private fun setBottomNavigation() {
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        .setupWithNavController(
            findNavController(R.id.nav_host_fragment)
        )
    }
}
