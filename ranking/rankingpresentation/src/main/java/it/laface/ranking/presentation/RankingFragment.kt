package it.laface.ranking.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import it.laface.base.CallState
import it.laface.common.view.BaseAdapter
import it.laface.common.viewModels
import it.laface.navigation.Navigator
import it.laface.ranking.domain.RankingDataSource
import it.laface.ranking.presentation.databinding.FragmentRankingBinding
import it.laface.team.domain.TeamPageProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RankingFragment(
    dataSource: RankingDataSource,
    teamPageProvider: TeamPageProvider,
    navigator: Navigator
) : Fragment() {

    private val viewModel: RankingViewModel by viewModels {
        RankingViewModel(
            dataSource = dataSource,
            jobDispatcher = Dispatchers.IO,
            teamPageProvider = teamPageProvider,
            navigator = navigator
        )
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
            RankingViewHolder(
                RecyclerView(it.context),
                viewModel::onTeamClicked
            )
        }.apply {
            list = listOf(emptyList(), emptyList())
        }
        viewPager.adapter = viewPagerAdapter

        val tabFont = ResourcesCompat.getFont(requireContext(), R.font.open_sans_bold)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = if (position == 0) "WEST" else "EAST"
            (tab.view.get(1) as? TextView)?.typeface = tabFont
        }.attach()

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.rankingListsCallState.collect { callState ->
                if (callState is CallState.Success) {
                    viewPagerAdapter.list =
                        listOf(callState.result.westCoastRanking, callState.result.eastCoastRanking)
                    progressBar.visibility = View.GONE
                }
            }
        }
    }
}
