package it.laface.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import it.laface.common.util.requireParcelable
import it.laface.common.view.BaseAdapter
import it.laface.common.view.bindImage
import it.laface.common.view.dpToPx
import it.laface.common.view.inflater
import it.laface.common.viewModels
import it.laface.domain.network.CallState
import it.laface.domain.datasource.ScheduleDataSource
import it.laface.domain.datasource.TeamRosterDataSource
import it.laface.domain.model.imageUrl
import it.laface.domain.navigation.Navigator
import it.laface.domain.navigation.PlayerDetailPageProvider
import it.laface.team.databinding.FragmentTeamBinding
import it.laface.team.databinding.ItemTeamgameBinding
import it.laface.team.databinding.ItemTeamplayerBinding
import it.laface.team.viewpager.BasePagerAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TeamFragment(
    rosterDataSource: TeamRosterDataSource,
    scheduleDataSource: ScheduleDataSource,
    navigator: Navigator,
    playerPageProvider: PlayerDetailPageProvider
) : Fragment() {

    private val viewModel: TeamViewModel by viewModels {
        TeamViewModel(
            team = requireParcelable(ARGUMENT_KEY),
            rosterDataSource = rosterDataSource,
            scheduleDataSource = scheduleDataSource,
            jobDispatcher = Dispatchers.IO,
            navigator = navigator,
            playerPageProvider = playerPageProvider
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentTeamBinding
            .inflate(inflater, container, false)
            .apply {
                setView()
            }
            .root

    private fun FragmentTeamBinding.setView() {
        cityNameTextView.text = viewModel.team.cityName
        nicknameTextView.text = viewModel.team.nickname

        teamImageView.bindImage(viewModel.team.imageUrl)

        teamInfoButton.setOnClickListener { /* TODO */ }

        setSchedule(gamesViewPager)
        setRoaster(rosterRecyclerView)

        backImageView.setOnClickListener {
            viewModel.navigateBack()
        }
    }

    private fun setSchedule(viewPager: ViewPager) {
        val scheduleAdapter =
            BasePagerAdapter(PAGE_WIDTH_PERCENTAGE) { parent ->
                GameViewHolder(
                    ItemTeamgameBinding.inflate(parent.inflater, parent, false)
                )
            }
        viewPager.pageMargin = resources.dpToPx(PAGE_MARGIN_DP).toInt()
        viewPager.adapter = scheduleAdapter

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.scheduleCallState.collect { callState ->
                if (callState is CallState.Success) {
                    scheduleAdapter.list = callState.result
                    viewPager.currentItem = viewModel.scrollScheduleToIndex()
                }
            }
        }
    }

    private fun setRoaster(recyclerView: RecyclerView) {
        val rosterAdapter = BaseAdapter { parent ->
            PlayerViewHolder(
                ItemTeamplayerBinding.inflate(parent.inflater, parent, false),
                viewModel::playerSelected
            )
        }
        val spans = resources.getInteger(R.integer.roster_columns)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), spans)
        recyclerView.adapter = rosterAdapter

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.rosterCallState.collect { callState ->
                if (callState is CallState.Success) {
                    rosterAdapter.list = callState.result
                }
            }
        }
    }

    companion object {

        private const val PAGE_MARGIN_DP = 8f
        private const val PAGE_WIDTH_PERCENTAGE = .8f
        internal const val ARGUMENT_KEY = "ARGUMENT_KEY"
    }
}
