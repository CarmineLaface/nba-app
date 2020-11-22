package it.laface.schedule.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import it.laface.common.ContentListToShow
import it.laface.common.ContentToShow
import it.laface.common.util.observe
import it.laface.common.view.BaseAdapter
import it.laface.common.view.goneUnless
import it.laface.common.view.inflater
import it.laface.common.viewModels
import it.laface.game.domain.Game
import it.laface.game.domain.GamePageProvider
import it.laface.game.domain.ScheduleDataSource
import it.laface.navigation.Navigator
import it.laface.schedule.presentation.databinding.FragmentScheduleBinding
import it.laface.schedule.presentation.databinding.ItemGameBinding
import kotlinx.coroutines.Dispatchers
import java.util.Date

class ScheduleFragment(
    dataSource: ScheduleDataSource,
    navigator: Navigator,
    gamePageProvider: GamePageProvider
) : Fragment() {

    private val viewModel: ScheduleViewModel by viewModels {
        ScheduleViewModel(
            dataSource = dataSource,
            jobDispatcher = Dispatchers.IO,
            gamePageProvider = gamePageProvider,
            navigator = navigator
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

    @Suppress("DEPRECATION")
    private fun FragmentScheduleBinding.setView() {
        val gameAdapter = getGameAdapter()
        gameRecyclerView.adapter = gameAdapter

        observe(viewModel.gamesToShow) { contentToShow ->
            bindContentToShow(contentToShow, gameAdapter)
        }

        calendarView.date = viewModel.selectedDate.value.time
        calendarView.setOnDateChangeListener { _, year, month, day ->
            viewModel.selectedDate.value = Date(year - BASE_YEAR, month, day)
        }
    }

    private fun FragmentScheduleBinding.bindContentToShow(
        contentToShow: ContentListToShow<Game>,
        gameAdapter: BaseAdapter<Game>
    ) {
        if (contentToShow is ContentToShow.Success) {
            gameRecyclerView.visibility = View.VISIBLE
            gameAdapter.list = contentToShow.content
        } else {
            gameRecyclerView.visibility = View.GONE
        }
        progressBar.goneUnless(contentToShow is ContentToShow.Loading)
        emptyListPlaceholder.goneUnless(contentToShow is ContentToShow.Placeholder)
    }

    private fun getGameAdapter(): BaseAdapter<Game> =
        BaseAdapter { parent ->
            GameViewHolder(
                ItemGameBinding.inflate(parent.inflater, parent, false),
                viewModel::onGameSelected
            )
        }

    companion object {
        private const val BASE_YEAR = 1900
    }
}
