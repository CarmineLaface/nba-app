package it.laface.common.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

inline fun <T> Fragment.observe(flow: Flow<T>, crossinline action: suspend (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
        flow.collect(action)
    }
}
