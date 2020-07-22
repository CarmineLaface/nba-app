package it.laface.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import it.laface.common.util.requireParcelable
import it.laface.common.view.bindImage
import it.laface.common.viewModels
import it.laface.domain.model.imageUrl
import it.laface.team.databinding.FragmentTeamBinding

class TeamFragment : Fragment() {

    private val viewModel: TeamViewModel by viewModels {
        TeamViewModel(
            requireParcelable(ARGUMENT_KEY)
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
    }

    companion object {

        internal const val ARGUMENT_KEY = "ARGUMENT_KEY"
    }
}
