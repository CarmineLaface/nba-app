package it.laface.game.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import it.laface.common.ContentToShow
import it.laface.common.util.getCompleteDayName
import it.laface.common.util.observe
import it.laface.common.util.requireSerializable
import it.laface.common.util.toCalendar
import it.laface.common.view.BaseAdapter
import it.laface.common.view.bindImage
import it.laface.common.view.inflater
import it.laface.common.viewModels
import it.laface.domain.model.fullName
import it.laface.domain.model.imageUrl
import it.laface.game.domain.GameDataSource
import it.laface.game.domain.GameInfo
import it.laface.game.presentation.databinding.FragmentGameBinding
import it.laface.game.presentation.databinding.ItemLeadersBinding
import it.laface.game.presentation.databinding.ItemLineScoreBinding
import it.laface.game.presentation.databinding.PlayersInfoBinding
import it.laface.game.presentation.databinding.TeamsInfoBinding
import it.laface.game.presentation.leaders.Leader
import it.laface.game.presentation.leaders.LeaderField
import it.laface.game.presentation.leaders.LeadersViewHolder
import it.laface.navigation.Navigator
import it.laface.team.domain.TeamPageProvider
import kotlinx.coroutines.Dispatchers
import it.laface.common.R as CR

class GameFragment(
    gameDataSource: GameDataSource,
    navigator: Navigator,
    teamPageProvider: TeamPageProvider,
) : Fragment() {

    private val viewModel: GameViewModel by viewModels {
        GameViewModel(
            game = requireSerializable(GAME_KEY),
            navigator = navigator,
            gameDataSource = gameDataSource,
            teamPageProvider = teamPageProvider,
            jobDispatcher = Dispatchers.IO,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentGameBinding
            .inflate(inflater, container, false)
            .apply {
                setView()
            }
            .root

    private fun FragmentGameBinding.setView() {
        backImageView.setOnClickListener {
            viewModel.navigateBack()
        }

        dateTextView.text = viewModel.game.date.toCalendar.getCompleteDayName
        observe(viewModel.gameInfoCallState) { contentToShow ->
            bindContentToShow(contentToShow)
        }

        val homeTeam = viewModel.game.homeTeam
        homeTeamNameTextView.text = homeTeam.fullName
        homeTeamNameTextView.setOnClickListener {
            viewModel.navigateToTeamPage(homeTeam)
        }
        homeTeamLogoImageView.bindImage(homeTeam.imageUrl, CR.drawable.circle_grey)
        homeTeamLogoImageView.setOnClickListener {
            viewModel.navigateToTeamPage(homeTeam)
        }
        homeScoreTextView.text = viewModel.game.homeScore

        val visitorTeam = viewModel.game.visitorTeam
        visitorTeamNameTextView.text = visitorTeam.fullName
        visitorTeamNameTextView.setOnClickListener {
            viewModel.navigateToTeamPage(visitorTeam)
        }
        visitorTeamLogoImageView.bindImage(visitorTeam.imageUrl, CR.drawable.circle_grey)
        visitorTeamLogoImageView.setOnClickListener {
            viewModel.navigateToTeamPage(visitorTeam)
        }
        visitorScoreTextView.text = viewModel.game.visitorScore
    }

    private fun FragmentGameBinding.bindContentToShow(contentToShow: ContentToShow<GameInfo>) {
        if (contentToShow is ContentToShow.Success) {
            tabLayout.setTabs(teamsInfo.root, playersInfo.root)
            teamsInfo.setLineScoreAdapter(contentToShow.content)
            playersInfo.setLeadersAdapter(contentToShow.content)
        }
        progressBar.isVisible = contentToShow is ContentToShow.Loading
        placeholderTextView.isVisible = contentToShow is ContentToShow.Placeholder
    }

    private fun TabLayout.setTabs(teamsInfo: View, playersInfo: View) {
        addTab(newTab().setText(getString(R.string.team_stats)))
        addTab(newTab().setText(getString(R.string.players_stats)))
        addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabUnselected(tab: TabLayout.Tab) = Unit
            override fun onTabReselected(tab: TabLayout.Tab) = Unit

            override fun onTabSelected(tab: TabLayout.Tab) {
                val firstPosition = tab.position == 0
                teamsInfo.isVisible = firstPosition
                playersInfo.isVisible = firstPosition.not()
            }
        })
    }

    private fun TeamsInfoBinding.setLineScoreAdapter(gameInfo: GameInfo) {
        val homeTeamLineScore = gameInfo.homeTeamGame.lineScore
        val visitorTeamLineScore = gameInfo.visitorTeamGame.lineScore
        val lineScore = homeTeamLineScore.zip(visitorTeamLineScore)
        linescoreRecyclerView.adapter = BaseAdapter(lineScore) { parent ->
            LineScoreViewHolder(
                ItemLineScoreBinding.inflate(parent.inflater, parent, false),
            )
        }
    }

    private fun PlayersInfoBinding.setLeadersAdapter(gameInfo: GameInfo) {
        val homePlayersStats = gameInfo.homeTeamGame.playersStats
        val visitorPlayersStats = gameInfo.visitorTeamGame.playersStats
        val leadersFields = listOf(
            LeaderField(
                name = "Points",
                homeLeader = Leader(
                    homePlayersStats.points.players[0],
                    homePlayersStats.points.value
                ),
                visitorLeader = Leader(
                    visitorPlayersStats.points.players[0],
                    visitorPlayersStats.points.value
                ),
            ),
            LeaderField(
                name = "Rebounds",
                homeLeader = Leader(
                    homePlayersStats.rebounds.players[0],
                    homePlayersStats.rebounds.value
                ),
                visitorLeader = Leader(
                    visitorPlayersStats.rebounds.players[0],
                    visitorPlayersStats.rebounds.value
                ),
            ),
            LeaderField(
                name = "Assists",
                homeLeader = Leader(
                    homePlayersStats.assists.players[0],
                    homePlayersStats.assists.value
                ),
                visitorLeader = Leader(
                    visitorPlayersStats.assists.players[0],
                    visitorPlayersStats.assists.value
                ),
            ),
        )
        leadersRecyclerView.adapter = BaseAdapter(leadersFields) { parent ->
            LeadersViewHolder(
                ItemLeadersBinding.inflate(parent.inflater, parent, false),
            )
        }
    }

    companion object {
        internal const val GAME_KEY = "GAME_KEY"
    }
}
