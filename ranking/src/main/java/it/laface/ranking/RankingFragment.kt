package it.laface.ranking

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import it.laface.common.view.BaseAdapter
import it.laface.common.viewModels
import it.laface.domain.CallState
import it.laface.domain.RankingDataSource
import kotlinx.android.synthetic.main.fragment_ranking.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RankingFragment(dataSource: RankingDataSource) : Fragment(R.layout.fragment_ranking) {

    private val viewModel: RankingViewModel by viewModels {
        RankingViewModel(dataSource, Dispatchers.IO)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager.adapter = BaseAdapter {
            RankingViewHolder(RecyclerView(it.context))
        }

        TabLayoutMediator(tab_layout, viewPager) { tab, position ->
            tab.text = if (position == 0) "WEST" else "EAST"
            viewPager.currentItem = tab.position
        }.attach()

        lifecycleScope.launch {
            viewModel.rankingListsCallState.collect {
                if (it is CallState.Success) {
                    viewPager.setPages(it.result.westCoastRanking, it.result.eastCoastRanking)
                }
            }
        }
    }
}
