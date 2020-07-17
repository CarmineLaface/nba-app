package it.laface.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import it.laface.common.view.BaseAdapter
import it.laface.common.view.inflater
import it.laface.common.viewModels
import it.laface.domain.datasource.ScheduleDataSource
import it.laface.domain.model.Game
import it.laface.schedule.databinding.FragmentScheduleBinding
import it.laface.schedule.databinding.ItemGameBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Date

@Suppress("DEPRECATION")
class ScheduleFragment(dataSource: ScheduleDataSource) : Fragment() {

    private val viewModel: ScheduleViewModel by viewModels {
        ScheduleViewModel(
            dataSource,
            Dispatchers.IO
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentScheduleBinding
            .inflate(inflater, container, false)
            .apply {
                setView()
            }
            .root

    private fun FragmentScheduleBinding.setView() {
        val adapter = getGameAdapter()
        gameRecyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.gamesToShow.collect {
                println("gamesToShow.collect $it")
                adapter.list = it
            }
        }

        calendarView.setOnDateChangeListener { _, year, month, day ->
            viewModel.selectedDate.value = Date(year - 1900, month, day)
        }
    }

    private fun getGameAdapter(): BaseAdapter<Game> =
        BaseAdapter { parent ->
            GameViewHolder(
                ItemGameBinding.inflate(parent.inflater, parent, false)
            )
        }
}
