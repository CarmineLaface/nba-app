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
import it.laface.common.view.inflate
import it.laface.common.viewModels
import it.laface.domain.PlayersDataSource
import it.laface.playerlist.databinding.FragmentPlayerListBinding
import kotlinx.coroutines.Dispatchers

class PlayerListFragment(dataSource: PlayersDataSource) : Fragment() {

    private val viewModel: PlayerListViewModel by viewModels {
        PlayerListViewModel(dataSource, Dispatchers.IO)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPlayers()
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
            PlayerViewHolder(parent.inflate(R.layout.item_player))
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
        playersAdapter: BaseAdapter<it.laface.domain.PlayerModel>
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
