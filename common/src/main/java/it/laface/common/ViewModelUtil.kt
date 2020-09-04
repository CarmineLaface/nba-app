package it.laface.common

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.savedstate.SavedStateRegistryOwner

inline fun <reified VM : ViewModel> SavedStateRegistryOwner.simpleViewModelFactory(
    crossinline viewModelProvider: (SavedStateHandle) -> VM
): () -> AbstractSavedStateViewModelFactory = {
    object : AbstractSavedStateViewModelFactory(this, null) {
        @Suppress("UNCHECKED_CAST")
        override fun <VM : ViewModel> create(
            key: String,
            modelClass: Class<VM>,
            handle: SavedStateHandle
        ): VM =
            viewModelProvider(handle) as VM
    }
}

@MainThread
inline fun <reified VM : ViewModel> Fragment.viewModels(
    noinline viewModelProvider: (SavedStateHandle) -> VM
): Lazy<VM> =
    ViewModelLazy(
        viewModelClass = VM::class,
        storeProducer = { viewModelStore },
        factoryProducer = simpleViewModelFactory(viewModelProvider)
    )
