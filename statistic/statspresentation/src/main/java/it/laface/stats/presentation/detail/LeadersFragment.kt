package it.laface.stats.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import it.laface.common.util.requireSerializable
import it.laface.common.view.BaseAdapter
import it.laface.common.view.inflater
import it.laface.common.viewModels
import it.laface.navigation.Navigator
import it.laface.stats.presentation.databinding.FragmentLeadersBinding
import it.laface.stats.presentation.databinding.ItemLeaderBinding

class LeadersFragment(navigator: Navigator) : Fragment() {

    private val viewModel: LeadersViewModel by viewModels {
        LeadersViewModel(requireSerializable(STATS_ARG_KEY), navigator)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentLeadersBinding
            .inflate(inflater, container, false)
            .apply {
                setView()
            }
            .root

    private fun FragmentLeadersBinding.setView() {
        titleTextView.text = viewModel.section.title

        backImageView.setOnClickListener {
            viewModel.onBackClicked()
        }

        val leaderAdapter = BaseAdapter { parent ->
            LeadersViewHolder(
                ItemLeaderBinding.inflate(parent.inflater, parent, false)
            ) {}
        }
        leaderAdapter.list = viewModel.section.players
        leaderRecyclerView.adapter = leaderAdapter
    }

    companion object {

        internal const val STATS_ARG_KEY = "STATS_ARG_KEY"
    }
}
