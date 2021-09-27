package it.laface.common

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider

inline fun <reified VM : ViewModel> simpleViewModelFactory(
    crossinline viewModelProvider: () -> VM
): () -> ViewModelProvider.Factory = {
    object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <VM : ViewModel> create(
            modelClass: Class<VM>,
        ): VM =
            viewModelProvider() as VM
    }
}

@MainThread
inline fun <reified VM : ViewModel> Fragment.viewModels(
    noinline viewModelProvider: () -> VM
): Lazy<VM> =
    ViewModelLazy(
        viewModelClass = VM::class,
        storeProducer = { viewModelStore },
        factoryProducer = simpleViewModelFactory(viewModelProvider)
    )
