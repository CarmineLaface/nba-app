package it.laface.team.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import it.laface.common.ContentToShow
import it.laface.common.util.observe
import it.laface.common.util.requireParcelable
import it.laface.common.view.BaseAdapter
import it.laface.common.view.bindImage
import it.laface.common.view.inflater
import it.laface.common.viewModels
import it.laface.domain.model.imageUrl
import it.laface.game.domain.GamePageProvider
import it.laface.navigation.Navigator
import it.laface.player.domain.PlayerDetailPageProvider
import it.laface.team.domain.TeamInfo
import it.laface.team.presentation.TeamSection.About
import it.laface.team.presentation.TeamSection.Roster
import it.laface.team.presentation.TeamSection.Schedule
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
    playerPageProvider: PlayerDetailPageProvider,
    gamePageProvider: GamePageProvider
) : Fragment() {

    private val viewModel: TeamViewModel by viewModels {
        TeamViewModel(
            team = requireParcelable(ARGUMENT_KEY),
            teamDataSourcesManager = teamDataSourcesManager,
            jobDispatcher = Dispatchers.IO,
            navigator = navigator,
            playerPageProvider = playerPageProvider,
            gamePageProvider = gamePageProvider
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
        contentRoster.bindRoaster()
        bindSections()

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
        championshipsTotalTextView.text = teamInfo.championships.size.toString()
        /*teamInfo.socialPages.forEach { socialPage ->

            socialPagesContainer.addView()
        }*/
    }

    private fun FragmentTeamBinding.bindSections() {
        aboutTextView.setOnClickListener {
            viewModel.onAboutClick()
        }
        rosterTextView.setOnClickListener {
            viewModel.onRosterClick()
        }
        scheduleTextView.setOnClickListener {
            viewModel.onScheduleClick()
        }
        val checkedSectionResId = when (viewModel.openSection.value) {
            About -> R.id.aboutTextView
            Schedule -> R.id.scheduleTextView
            Roster -> R.id.rosterTextView
        }
        buttonGroup.check(checkedSectionResId)
        buttonGroup.addOnButtonCheckedListener { _, checkedResId, isChecked ->
            if (isChecked.not()) return@addOnButtonCheckedListener
            viewModel.openSection.value = when (checkedResId) {
                R.id.aboutTextView -> About
                R.id.scheduleTextView -> Schedule
                R.id.rosterTextView -> Roster
                else -> error("invalid checked resource id")
            }
        }
        observe(viewModel.openSection) { teamSection ->
            when (teamSection) {
                About -> {
                    contentAbout.root.visibility = View.VISIBLE
                    contentRoster.root.visibility = View.GONE
                    contentSchedule.root.visibility = View.GONE
                }
                Schedule -> {
                    contentAbout.root.visibility = View.GONE
                    contentRoster.root.visibility = View.GONE
                    contentSchedule.root.visibility = View.VISIBLE
                }
                Roster -> {
                    contentAbout.root.visibility = View.GONE
                    contentRoster.root.visibility = View.VISIBLE
                    contentSchedule.root.visibility = View.GONE
                }
            }
        }
    }

    private fun ContentScheduleBinding.bindSchedule() {
        val scheduleAdapter = BaseAdapter { parent ->
            GameViewHolder(
                ItemTeamgameBinding.inflate(parent.inflater, parent, false),
                viewModel::onGameSelected
            )
        }
        scheduleRecyclerView.adapter = scheduleAdapter

        observe(viewModel.scheduleContent) { contentToShow ->
            if (contentToShow is ContentToShow.Success) {
                scheduleAdapter.list = contentToShow.content
                scheduleRecyclerView.scrollToPosition(
                    viewModel.scrollScheduleToIndex(contentToShow.content)
                )
            }
            scheduleProgressBar.isVisible = contentToShow is ContentToShow.Loading
            scheduleErrorTextView.isVisible = contentToShow is ContentToShow.Error
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
            rosterProgressBar.isVisible = contentToShow is ContentToShow.Loading
            rosterErrorTextView.isVisible = contentToShow is ContentToShow.Error
        }
    }

    companion object {

        internal const val ARGUMENT_KEY = "ARGUMENT_KEY"
    }
}
