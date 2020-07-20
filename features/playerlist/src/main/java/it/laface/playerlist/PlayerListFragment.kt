package it.laface.playerlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import it.laface.common.view.BaseAdapter
import it.laface.common.view.goneUnless
import it.laface.common.view.inflater
import it.laface.common.viewModels
import it.laface.domain.datasource.PlayersDataSource
import it.laface.domain.model.PlayerModel
import it.laface.domain.navigation.PlayerDetailNavigationProvider
import it.laface.playerlist.databinding.FragmentPlayerListBinding
import it.laface.playerlist.databinding.ItemPlayerBinding
import kotlinx.coroutines.Dispatchers

class PlayerListFragment(
    dataSource: PlayersDataSource,
    navigationProvider: PlayerDetailNavigationProvider
) : Fragment() {

    private val viewModel: PlayerListViewModel by viewModels {
        PlayerListViewModel(dataSource, navigationProvider, Dispatchers.IO)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentPlayerListBinding
            .inflate(inflater, container, false)
            .apply { setView() }.root

    private fun FragmentPlayerListBinding.setView() {
        val playersAdapter = BaseAdapter { parent ->
            PlayerViewHolder(
                ItemPlayerBinding.inflate(parent.inflater, parent, false),
                viewModel::onPlayerSelected
            )
        }
        playersRecyclerView.adapter = playersAdapter
        viewModel.contentToShow.observe(viewLifecycleOwner) {
            bindContentToShow(it, playersAdapter)
        }
        playerNameEditText.setText(viewModel.nameToFilter.value)
        playerNameEditText.doAfterTextChanged { text ->
            viewModel.setNameToFilter(text.toString())
        }
        retryButton.setOnClickListener { viewModel.onRetry() }
    }

    private fun FragmentPlayerListBinding.bindContentToShow(
        contentToShow: ContentToShow,
        playersAdapter: BaseAdapter<PlayerModel>
    ) {
        if (contentToShow is ContentToShow.Success) {
            playersRecyclerView.visibility = View.VISIBLE
            playersAdapter.list = contentToShow.filteredList
        } else {
            playersRecyclerView.visibility = View.GONE
        }
        progressBar.goneUnless(contentToShow is ContentToShow.Loading)
        retryButton.goneUnless(contentToShow is ContentToShow.Error)
        emptyListPlaceholder.goneUnless(contentToShow is ContentToShow.Placeholder)
    }
}
