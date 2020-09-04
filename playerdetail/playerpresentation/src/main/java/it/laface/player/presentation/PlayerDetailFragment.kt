package it.laface.player.presentation

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import it.laface.common.util.requireParcelable
import it.laface.common.view.bindImage
import it.laface.common.viewModels
import it.laface.domain.model.fullName
import it.laface.navigation.Navigator
import it.laface.player.domain.PlayerStatsDataSource
import it.laface.player.domain.imageUrl
import it.laface.player.presentation.databinding.FragmentPlayerBinding
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
        viewModel.team.rgbColor?.let { setTeamColor(it) }

        teamNameTextView.setOnClickListener {
            viewModel.navigateToTeamPage()
        }

        favouriteImageView.bindFavourite(viewModel.isFavourite.value)
        favouriteImageView.setOnClickListener {
            val isFavourite = viewModel.isFavourite.value.not()
            viewModel.isFavourite.value = isFavourite
            favouriteImageView.bindFavourite(isFavourite)
        }
    }

    private fun FragmentPlayerBinding.setTeamColor(rgbColor: String) {
        val color = Color.parseColor(rgbColor)
        teamNameTextView.setTextColor(color)
        jerseyNameTextView.setTextColor(color)
        positionValueTextView.setTextColor(color)
        toolbar.setBackgroundColor(color)
        backImageView.backgroundTintList = ColorStateList.valueOf(color)
    }

    private fun ImageView.bindFavourite(isFavourite: Boolean) {
        setImageResource(
            if (isFavourite) R.drawable.ic_filled_star else R.drawable.ic_empty_star
        )
    }

    companion object {

        internal const val ARGUMENT_KEY = "ARGUMENT_KEY"
    }
}
