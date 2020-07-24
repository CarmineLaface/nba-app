package it.laface.team

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.laface.common.util.requireParcelable
import it.laface.common.view.BaseAdapter
import it.laface.common.view.bindImage
import it.laface.common.view.inflater
import it.laface.common.view.isLandScape
import it.laface.common.viewModels
import it.laface.domain.CallState
import it.laface.domain.datasource.ScheduleDataSource
import it.laface.domain.datasource.TeamRosterDataSource
import it.laface.domain.model.imageUrl
import it.laface.domain.navigation.Navigator
import it.laface.domain.navigation.PlayerDetailPageProvider
import it.laface.team.databinding.FragmentTeamBinding
import it.laface.team.databinding.ItemTeamgameBinding
import it.laface.team.databinding.ItemTeamplayerBinding
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
        cityNameTextView.text = viewModel.team.name
        nicknameTextView.text = viewModel.team.nickname

        teamImageView.bindImage(viewModel.team.imageUrl)

        teamInfoButton.setOnClickListener {
            // TODO
        }

        setSchedule(gamesRecyclerView)
        setRoaster(rosterRecyclerView)

        viewModel.team.rgbColor?.let {
            val color = Color.parseColor(it)
            toolbar.setBackgroundColor(color)
            backImageView.backgroundTintList = ColorStateList.valueOf(color)
        }
    }

    private fun setSchedule(recyclerView: RecyclerView) {
        val scheduleAdapter = BaseAdapter { parent ->
            GameViewHolder(
                ItemTeamgameBinding.inflate(parent.inflater, parent, false)
            )
        }
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = scheduleAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.scheduleCallState.collect { callState ->
                when (callState) {
                    is CallState.Success -> scheduleAdapter.list = callState.result
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
        val spans = if (requireContext().isLandScape) 5 else 3
        recyclerView.layoutManager = GridLayoutManager(requireContext(), spans)
        recyclerView.adapter = rosterAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.rosterCallState.collect { callState ->
                when (callState) {
                    is CallState.Success -> rosterAdapter.list = callState.result
                }
            }
        }
    }

    companion object {

        internal const val ARGUMENT_KEY = "ARGUMENT_KEY"
    }
}
