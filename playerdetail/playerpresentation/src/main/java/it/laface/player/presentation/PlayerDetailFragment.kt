package it.laface.player.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import it.laface.base.CallState
import it.laface.common.util.observe
import it.laface.common.util.requireParcelable
import it.laface.common.view.BaseAdapter
import it.laface.common.view.bindImage
import it.laface.common.view.inflater
import it.laface.common.viewModels
import it.laface.domain.model.fullName
import it.laface.navigation.Navigator
import it.laface.player.domain.PlayerStatsDataSource
import it.laface.player.domain.imageUrl
import it.laface.player.presentation.databinding.FragmentPlayerBinding
import it.laface.player.presentation.databinding.ItemStatisticBinding
import it.laface.team.domain.TeamPageProvider
import it.laface.team.domain.TeamRepository
import kotlinx.coroutines.Dispatchers

class PlayerDetailFragment(
    teamRepository: TeamRepository,
    playerStatsDataSource: PlayerStatsDataSource,
    navigator: Navigator,
    teamPageProvider: TeamPageProvider
) : Fragment() {

    private val viewModel: PlayerDetailViewModel by viewModels {
        PlayerDetailViewModel(
            player = requireParcelable(ARGUMENT_KEY),
            teamRepository = teamRepository,
            playerStatsDataSource = playerStatsDataSource,
            jobDispatcher = Dispatchers.IO,
            navigator = navigator,
            teamPageProvider = teamPageProvider
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentPlayerBinding
            .inflate(inflater, container, false)
            .apply {
                setView()
            }
            .root

    private fun FragmentPlayerBinding.setView() {
        nameTextView.text = viewModel.player.name
        surnameTextView.text = viewModel.player.surname
        playerImageView.bindImage(viewModel.player.imageUrl, R.drawable.player_placeholder)

        jerseyNameTextView.text = viewModel.player.jerseyNumber
        positionValueTextView.text = viewModel.player.position

        backImageView.setOnClickListener {
            viewModel.goBack()
        }

        teamNameTextView.text = viewModel.team.fullName

        teamNameTextView.setOnClickListener {
            viewModel.navigateToTeamPage()
        }

        val statsAdapter = BaseAdapter { parent ->
            StatsViewHolder(
                ItemStatisticBinding.inflate(parent.inflater, parent, false)
            )
        }
        statsRecyclerView.adapter = statsAdapter
        observe(viewModel.playerStatsCallState) { callState ->
            if (callState is CallState.Success) {
                statsAdapter.list = callState.result
            }
        }
    }

    companion object {

        internal const val ARGUMENT_KEY = "ARGUMENT_KEY"
    }
}
