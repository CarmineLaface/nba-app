package it.laface.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

inline fun <T1, T2, R> LiveData<T1>.combine(
    liveData: LiveData<T2>,
    crossinline block: (T1?, T2?) -> R
): MediatorLiveData<R> {
    return MediatorLiveData<R>().apply {
        addSource(this@combine) {
            val newValue = block(it, liveData.value)
            if (newValue != value) {
                value = newValue
            }
        }
        addSource(liveData) {
            val newValue = block(this@combine.value, it)
            if (newValue != value) {
                value = newValue
            }
        }
    }
}
