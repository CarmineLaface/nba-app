package it.laface.fantanba

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView
import it.laface.news.NewsFragment
import it.laface.playerlist.PlayerListFragment
import it.laface.ranking.RankingFragment
import it.laface.schedule.ScheduleFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = CustomFragmentFactory
        super.onCreate(savedInstanceState)

        setBottomNavigation()

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                val firstFragmentClass = NewsFragment::class.java
                add(R.id.container, firstFragmentClass, null, firstFragmentClass.name)
            }
        }
    }

    private fun setBottomNavigation() {
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            .setOnNavigationItemSelectedListener { item ->
                moveTo(getBottomNavigationSection(item.itemId))
                return@setOnNavigationItemSelectedListener true
            }
    }

    private fun moveTo(newFragment: Class<Fragment>) {
        supportFragmentManager.commit {
            replace(R.id.container, newFragment, null, newFragment.name)
            addToBackStack(newFragment.name)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun getBottomNavigationSection(itemId: Int): Class<Fragment> {
        return when (itemId) {
            R.id.news -> NewsFragment::class.java
            R.id.players -> PlayerListFragment::class.java
            R.id.ranking -> RankingFragment::class.java
            R.id.schedule -> ScheduleFragment::class.java
            else -> NewsFragment::class.java
        } as Class<Fragment>
    }
}
