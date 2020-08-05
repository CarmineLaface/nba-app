package it.laface.playerdetail

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import it.laface.common.util.requireParcelable
import it.laface.common.view.bindImage
import it.laface.common.viewModels
import it.laface.domain.datasource.TeamRepository
import it.laface.domain.model.fullName
import it.laface.domain.model.imageUrl
import it.laface.domain.navigation.Navigator
import it.laface.playerdetail.databinding.FragmentPlayerBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlayerDetailFragment(teamRepository: TeamRepository, navigator: Navigator) : Fragment() {

    private val viewModel: PlayerDetailViewModel by viewModels {
        PlayerDetailViewModel(
            requireParcelable(ARGUMENT_KEY),
            teamRepository,
            navigator
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

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.team.collect { team ->
                teamNameTextView.text = team.fullName
                team.rgbColor?.let { setTeamColor(it) }
            }
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

    companion object {

        internal const val ARGUMENT_KEY = "ARGUMENT_KEY"
    }
}
