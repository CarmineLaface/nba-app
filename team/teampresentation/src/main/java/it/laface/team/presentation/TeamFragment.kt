package it.laface.team.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import it.laface.common.ContentToShow
import it.laface.common.util.observe
import it.laface.common.util.requireParcelable
import it.laface.common.view.BaseAdapter
import it.laface.common.view.bindImage
import it.laface.common.view.goneUnless
import it.laface.common.view.inflater
import it.laface.common.viewModels
import it.laface.domain.model.imageUrl
import it.laface.navigation.Navigator
import it.laface.player.domain.PlayerDetailPageProvider
import it.laface.team.domain.TeamInfo
import it.laface.team.presentation.databinding.ContentAboutBinding
import it.laface.team.presentation.databinding.ContentRosterBinding
import it.laface.team.presentation.databinding.ContentScheduleBinding
import it.laface.team.presentation.databinding.FragmentTeamBinding
import it.laface.team.presentation.databinding.ItemTeamgameBinding
import it.laface.team.presentation.databinding.ItemTeamplayerBinding
import kotlinx.coroutines.Dispatchers

class TeamFragment(
    teamDataSourcesManager: TeamDataSourcesManager,
    navigator: Navigator,
    playerPageProvider: PlayerDetailPageProvider
) : Fragment() {

    private val viewModel: TeamViewModel by viewModels {
        TeamViewModel(
            team = requireParcelable(ARGUMENT_KEY),
            teamDataSourcesManager = teamDataSourcesManager,
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
        cityTextView.text = viewModel.team.cityName
        nameTextView.text = viewModel.team.nickname

        teamImageView.bindImage(viewModel.team.imageUrl)

        contentSchedule.bindSchedule()
        bindScheduleAccordion()
        contentRoster.bindRoaster()
        bindRosterAccordion()
        bindAboutAccordion()

        observe(viewModel.teamInfoContent) { contentToShow ->
            if (contentToShow is ContentToShow.Success) {
                contentAbout.bindAbout(contentToShow.content)
            }
        }

        backImageView.setOnClickListener {
            viewModel.navigateBack()
        }
    }

    private fun ContentAboutBinding.bindAbout(teamInfo: TeamInfo) {
        yearFoundedNameTextView.text = teamInfo.yearFounded
        arenaNameTextView.text = teamInfo.arena
        ownerNameTextView.text = teamInfo.owner
        generalManagerNameTextView.text = teamInfo.generalManager
        headCoachNameTextView.text = teamInfo.headCoach
    }

    private fun FragmentTeamBinding.bindAboutAccordion() {
        aboutTextView.setOnClickListener {
            viewModel.onAboutClick()
        }
        observe(viewModel.isAboutOpen) { isOpen ->
            contentAbout.root.goneUnless(isOpen)
            aboutAccordion.rotateAccordion(isOpen)
        }
    }

    private fun FragmentTeamBinding.bindRosterAccordion() {
        rosterTextView.setOnClickListener {
            viewModel.onRosterClick()
        }
        observe(viewModel.isRosterOpen) { isOpen ->
            contentRoster.root.goneUnless(isOpen)
            rosterAccordion.rotateAccordion(isOpen)
        }
    }

    private fun FragmentTeamBinding.bindScheduleAccordion() {
        scheduleTextView.setOnClickListener {
            viewModel.onScheduleClick()
        }
        observe(viewModel.isScheduleOpen) { isOpen ->
            contentSchedule.root.goneUnless(isOpen)
            scheduleAccordion.rotateAccordion(isOpen)
        }
    }

    private fun ContentScheduleBinding.bindSchedule() {
        val scheduleAdapter = BaseAdapter { parent ->
            GameViewHolder(
                ItemTeamgameBinding.inflate(parent.inflater, parent, false)
            )
        }
        scheduleRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        scheduleRecyclerView.adapter = scheduleAdapter
        LinearSnapHelper().attachToRecyclerView(scheduleRecyclerView)

        observe(viewModel.scheduleContent) { contentToShow ->
            if (contentToShow is ContentToShow.Success) {
                scheduleAdapter.list = contentToShow.content
                scheduleRecyclerView.scrollToPosition(
                    viewModel.scrollScheduleToIndex(contentToShow.content)
                )
            }
            scheduleProgressBar.goneUnless(contentToShow is ContentToShow.Loading)
            scheduleErrorTextView.goneUnless(contentToShow is ContentToShow.Error)
        }
    }

    private fun ContentRosterBinding.bindRoaster() {
        val rosterAdapter = BaseAdapter { parent ->
            PlayerViewHolder(
                ItemTeamplayerBinding.inflate(parent.inflater, parent, false),
                viewModel::playerSelected
            )
        }
        rosterRecyclerView.adapter = rosterAdapter

        observe(viewModel.rosterContent) { contentToShow ->
            if (contentToShow is ContentToShow.Success) {
                rosterAdapter.list = contentToShow.content
            }
            rosterProgressBar.goneUnless(contentToShow is ContentToShow.Loading)
            rosterErrorTextView.goneUnless(contentToShow is ContentToShow.Error)
        }
    }

    private fun View.rotateAccordion(isOpen: Boolean) {
        rotation = if (isOpen) 180f else 0f
    }
    companion object {

        internal const val ARGUMENT_KEY = "ARGUMENT_KEY"
    }
}
