package it.laface.fantanba

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView
import it.laface.navigation.Page
import it.laface.news.presentation.NewsFragment
import it.laface.playerlist.presentation.PlayerListFragment
import it.laface.ranking.presentation.RankingFragment
import it.laface.schedule.presentation.ScheduleFragment

@Suppress("UNCHECKED_CAST")
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val firstFragmentClass: Class<Fragment>
        get() = NewsFragment::class.java as Class<Fragment>

    private val bottomNavigationSections: Map<Int, Class<*>> = mapOf(
        R.id.news to NewsFragment::class.java,
        R.id.players to PlayerListFragment::class.java,
        R.id.ranking to RankingFragment::class.java,
        R.id.schedule to ScheduleFragment::class.java,
    )

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
            .setOnNavigationItemSelectedListener { item ->
                val newFragment = bottomNavigationSections[item.itemId] as Class<Fragment>
                CustomFragmentFactory.navigator.navigateForward(Page(newFragment))
                return@setOnNavigationItemSelectedListener true
            }
    }
}
