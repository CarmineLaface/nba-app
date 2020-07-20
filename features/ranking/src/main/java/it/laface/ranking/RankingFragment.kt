package it.laface.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import it.laface.common.view.BaseAdapter
import it.laface.common.viewModels
import it.laface.domain.CallState
import it.laface.domain.datasource.RankingDataSource
import it.laface.ranking.databinding.FragmentRankingBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RankingFragment(dataSource: RankingDataSource) : Fragment(R.layout.fragment_ranking) {

    private val viewModel: RankingViewModel by viewModels {
        RankingViewModel(dataSource, Dispatchers.IO)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentRankingBinding
            .inflate(inflater, container, false)
            .apply {
                setView()
            }.root

    private fun FragmentRankingBinding.setView() {
        val viewPagerAdapter = BaseAdapter {
            RankingViewHolder(RecyclerView(it.context))
        }.apply {
            list = listOf(emptyList(), emptyList())
        }
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = if (position == 0) "WEST" else "EAST"
            viewPager.currentItem = tab.position
        }.attach()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.rankingListsCallState.collect {
                if (it is CallState.Success) {
                    viewPagerAdapter.list =
                        listOf(it.result.westCoastRanking, it.result.eastCoastRanking)
                }
            }
        }
    }
}
