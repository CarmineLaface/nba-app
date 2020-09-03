package it.laface.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import it.laface.common.ContentToShow
import it.laface.common.util.requireParcelable
import it.laface.common.view.BaseAdapter
import it.laface.common.view.bindImage
import it.laface.common.view.goneUnless
import it.laface.common.view.inflater
import it.laface.common.viewModels
import it.laface.domain.model.imageUrl
import it.laface.navigation.Navigator
import it.laface.player.domain.PlayerDetailPageProvider
import it.laface.player.domain.TeamRosterDataSource
import it.laface.schedule.domain.ScheduleDataSource
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
        cityNameTextView.text = viewModel.team.cityName
        nicknameTextView.text = viewModel.team.nickname

        teamImageView.bindImage(viewModel.team.imageUrl)

        teamInfoButton.setOnClickListener { /* TODO */ }

        setSchedule()
        setRoaster()

        backImageView.setOnClickListener {
            viewModel.navigateBack()
        }
    }

    private fun FragmentTeamBinding.setSchedule() {
        val scheduleAdapter =
            BaseAdapter { parent ->
                GameViewHolder(
                    ItemTeamgameBinding.inflate(parent.inflater, parent, false)
                )
            }
        scheduleRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        scheduleRecyclerView.adapter = scheduleAdapter
        PagerSnapHelper().attachToRecyclerView(scheduleRecyclerView)

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.scheduleContent.collect { contentToShow ->
                if (contentToShow is ContentToShow.Success) {
                    scheduleAdapter.list = contentToShow.contentList
                    scheduleRecyclerView.scrollToPosition(
                        viewModel.scrollScheduleToIndex(contentToShow.contentList)
                    )
                }
                scheduleProgressBar.goneUnless(contentToShow is ContentToShow.Loading)
                scheduleErrorTextView.goneUnless(contentToShow is ContentToShow.Error)
            }
        }
    }

    private fun FragmentTeamBinding.setRoaster() {
        val rosterAdapter = BaseAdapter { parent ->
            PlayerViewHolder(
                ItemTeamplayerBinding.inflate(parent.inflater, parent, false),
                viewModel::playerSelected
            )
        }
        rosterRecyclerView.adapter = rosterAdapter

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.rosterContent.collect { contentToShow ->
                if (contentToShow is ContentToShow.Success) {
                    rosterAdapter.list = contentToShow.contentList
                }
                rosterProgressBar.goneUnless(contentToShow is ContentToShow.Loading)
                rosterErrorTextView.goneUnless(contentToShow is ContentToShow.Error)
            }
        }
    }

    companion object {

        internal const val ARGUMENT_KEY = "ARGUMENT_KEY"
    }
}
