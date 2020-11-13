package it.laface.game.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import it.laface.common.util.requireParcelable
import it.laface.common.view.bindImage
import it.laface.common.viewModels
import it.laface.domain.model.fullName
import it.laface.domain.model.imageUrl
import it.laface.game.domain.GameDataSource
import it.laface.game.presentation.databinding.FragmentGameBinding
import kotlinx.coroutines.Dispatchers

class GameFragment(
    gameDataSource: GameDataSource
) : Fragment() {

    private val viewModel: GameViewModel by viewModels {
        GameViewModel(
            requireParcelable(GAME_KEY),
            gameDataSource,
            Dispatchers.IO
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
        val homeTeam = viewModel.game.homeTeam
        homeTeamNameTextView.text = homeTeam.fullName
        homeTeamLogoImageView.bindImage(homeTeam.imageUrl, R.drawable.circle_grey)
        homeScoreTextView.text = viewModel.game.homeScore ?: "-"

        val visitorTeam = viewModel.game.visitorTeam
        visitorTeamNameTextView.text = visitorTeam.fullName
        visitorTeamLogoImageView.bindImage(visitorTeam.imageUrl, R.drawable.circle_grey)
        visitorScoreTextView.text = viewModel.game.visitorScore ?: "-"
    }

    companion object {
        internal const val GAME_KEY = "GAME_KEY"
    }
}
