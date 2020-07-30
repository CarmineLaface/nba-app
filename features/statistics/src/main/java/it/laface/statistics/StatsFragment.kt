package it.laface.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import it.laface.common.view.BaseAdapter
import it.laface.common.view.inflater
import it.laface.common.viewModels
import it.laface.domain.CallState.Success
import it.laface.domain.datasource.StatsDataSource
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
                ItemStatisticBinding.inflate(parent.inflater, parent, false)
            ) {}
        }
        groupRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        groupRecyclerView.adapter = groupAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.statsCallState.collect {
                when (it) {
                    is Success -> {
                        groupAdapter.list = it.result
                    }
                }
            }
        }
    }
}
