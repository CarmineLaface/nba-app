package it.laface.game.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import it.laface.common.ContentToShow
import it.laface.common.util.getCompleteDayName
import it.laface.common.util.observe
import it.laface.common.util.requireParcelable
import it.laface.common.util.toCalendar
import it.laface.common.view.bindImage
import it.laface.common.view.goneUnless
import it.laface.common.view.setGone
import it.laface.common.viewModels
import it.laface.domain.model.imageUrl
import it.laface.game.domain.GameDataSource
import it.laface.game.domain.GameInfo
import it.laface.game.presentation.databinding.FragmentGameBinding
import it.laface.navigation.Navigator
import kotlinx.coroutines.Dispatchers

class GameFragment(
    gameDataSource: GameDataSource,
    navigator: Navigator
) : Fragment() {

    private val viewModel: GameViewModel by viewModels {
        GameViewModel(
            game = requireParcelable(GAME_KEY),
            navigator = navigator,
            gameDataSource = gameDataSource,
            jobDispatcher = Dispatchers.IO
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
        homeTeamNameTextView.text = homeTeam.nickname
        homeCityNameTextView.text = homeTeam.cityName
        homeTeamLogoImageView.bindImage(homeTeam.imageUrl, R.drawable.circle_grey)
        viewModel.game.homeScore?.let { score ->
            homeScoreTextView.text = score
        } ?: homeScoreTextView.setGone()

        val visitorTeam = viewModel.game.visitorTeam
        visitorTeamNameTextView.text = visitorTeam.nickname
        visitorCityNameTextView.text = visitorTeam.cityName
        visitorTeamLogoImageView.bindImage(visitorTeam.imageUrl, R.drawable.circle_grey)
        viewModel.game.visitorScore?.let { score ->
            visitorScoreTextView.text = score
        } ?: visitorScoreTextView.setGone()
    }

    private fun FragmentGameBinding.bindContentToShow(contentToShow: ContentToShow<GameInfo>) {
        if (contentToShow is ContentToShow.Success) {
            nuggetTextView.text = contentToShow.content.nugget
        }
        progressBar.goneUnless(contentToShow is ContentToShow.Loading)
        placeholderTextView.goneUnless(contentToShow is ContentToShow.Placeholder)
    }

    companion object {
        internal const val GAME_KEY = "GAME_KEY"
    }
}
