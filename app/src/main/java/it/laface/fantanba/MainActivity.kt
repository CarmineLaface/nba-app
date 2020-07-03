package it.laface.fantanba

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import it.laface.news.NewsFragment
import it.laface.playerlist.PlayerListFragment
import it.laface.ranking.RankingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = CustomFragmentFactory
        super.onCreate(savedInstanceState)

        setBottomNavigation()

        val firstFragmentClass = NewsFragment::class.java

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.container, firstFragmentClass, null, firstFragmentClass.name)
            }
        }
    }

    private fun setBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
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
            R.id.schedule -> Nothing::class.java
            else -> throw IllegalStateException("itemId not found")
        } as Class<Fragment>
    }
}
