package it.laface.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import it.laface.common.util.BrowserProviderImpl
import it.laface.common.viewModels
import it.laface.domain.datasource.ScheduleDataSource
import it.laface.schedule.databinding.FragmentScheduleBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            .root
}