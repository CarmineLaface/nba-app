package it.laface.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    // region -------------------- swipe to refresh --------------------

    @Test
    fun `given a successful state WHEN the swipe to refresh action triggers a network call that end successfully THEN the current state should be updated with the given list`() {
    }

    @Test
    fun `given a successful state list WHEN the swipe to refresh action triggers a network call that fails THEN the current state should not be updated and a one shot message should be displayed`() {
    }

    // endregion
}
