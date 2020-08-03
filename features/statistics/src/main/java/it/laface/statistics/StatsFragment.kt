package it.laface.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import it.laface.common.view.BaseAdapter
import it.laface.common.view.goneUnless
import it.laface.common.view.inflater
import it.laface.common.viewModels
import it.laface.domain.CallState.Success
import it.laface.domain.datasource.StatsDataSource
import it.laface.domain.model.StatsGroup
import it.laface.domain.navigation.Navigator
import it.laface.statistics.databinding.FragmentStatsBinding
import it.laface.statistics.databinding.ItemStatisticBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StatsFragment(
    navigator: Navigator,
    statsDataSource: StatsDataSource
) : Fragment() {

    private val viewModel: StatsViewModel by viewModels {
        StatsViewModel(
            navigator = navigator,
            statsDataSource = statsDataSource,
            jobDispatcher = Dispatchers.IO
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentStatsBinding
            .inflate(inflater, container, false)
            .apply {
                setView()
            }
            .root

    private fun FragmentStatsBinding.setView() {
        val groupAdapter = BaseAdapter { parent ->
            StatsGroupViewHolder(
                ItemStatisticBinding.inflate(parent.inflater, parent, false),
                viewModel::onStatsClicked
            )
        }
        backImageView.setOnClickListener {
            viewModel.navigateBack()
        }
        groupRecyclerView.adapter = groupAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.statsCallState.collect { contentToShow ->
                bindContentToShow(contentToShow, groupAdapter)
            }
        }
    }

    private fun FragmentStatsBinding.bindContentToShow(
        contentToShow: ContentToShow,
        newsAdapter: BaseAdapter<StatsGroup>
    ) {
        if (contentToShow is ContentToShow.Success) {
            groupRecyclerView.visibility = View.VISIBLE
            newsAdapter.list = contentToShow.statsGroup
        } else {
            groupRecyclerView.visibility = View.GONE
        }
        progressBar.goneUnless(contentToShow is ContentToShow.Loading)
    }
}
