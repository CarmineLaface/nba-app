package it.laface.playerlist.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListNotEmpty
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import io.mockk.mockk
import io.mockk.coEvery
import it.laface.base.NetworkError
import it.laface.base.NetworkResult
import it.laface.navigation.Navigator
import it.laface.player.domain.Player
import it.laface.player.domain.PlayerDetailPageProvider
import it.laface.player.domain.PlayersDataSource
import it.laface.stats.domain.StatsPageProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlayerListTest {

    @Rule
    @JvmField
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private val nbaDataSource: PlayersDataSource = mockk()
    private val playerDetailPageProvider: PlayerDetailPageProvider = mockk()
    private val navigator: Navigator = mockk()
    private val statsPageProvider: StatsPageProvider = mockk()
    private val errorResponse =
        NetworkResult.Failure(NetworkError.UnknownError("error"))

    // region -------------------- base tests --------------------
/*
    @Test
    fun `on page first opening WHEN the network call is in progress THEN there should be a loading state`() {

    }*/

    @Test
    fun `on page first opening WHEN the network call fails then THEN there should be an error state`() {
        runBlocking {
            coEvery { nbaDataSource.getPlayers() } returns errorResponse
            launchFragment()

            assertNotDisplayed(R.id.playersRecyclerView)
            assertNotDisplayed(R.id.emptyListPlaceholder)
            assertDisplayed(R.id.retryButton)
            assertNotDisplayed(R.id.progressBar)
        }
    }

    @Test
    fun `on page first opening WHEN the network call succeeds then THEN there should be a state with the given list`() {
        runBlocking {
            coEvery { nbaDataSource.getPlayers() } returns successFulResponse
            launchFragment()

            assertListNotEmpty(R.id.playersRecyclerView)
            assertNotDisplayed(R.id.emptyListPlaceholder)
            assertNotDisplayed(R.id.retryButton)
            assertNotDisplayed(R.id.progressBar)
        }
    }

    // endregion

    // region -------------------- retry --------------------

    @Test
    fun `given an error state WHEN a retry action triggers a network call that end successfully THEN the current state should be updated with the given list`() {
        runBlocking {
            coEvery { nbaDataSource.getPlayers() } returns errorResponse
            launchFragment()
            coEvery { nbaDataSource.getPlayers() } returns successFulResponse

            clickOn(R.id.retryButton)

            assertListNotEmpty(R.id.playersRecyclerView)
            assertNotDisplayed(R.id.retryButton)
        }
    }
    /*
        @Test
        fun `given an error state WHEN a retry action triggers a network call that fails THEN the current state should be error`() {
            runBlockingTest {
                viewModel.contentToShow.observeForever { }
                whenever(nbaDataSource.getPlayers()) thenReturn errorResponse()
                viewModel.getPlayers()
                whenever(nbaDataSource.getPlayers()) thenReturn errorResponse()

                viewModel.onRetry()

                assertEquals(ContentToShow.Error, viewModel.contentToShow.value)
            }
        }

        // endregion

        // region -------------------- filter for name --------------------

        @Test
        fun `given a successful state with a not empty player list WHEN a name is entered THEN there should be a state with a list filtered by that name`() {
            runBlockingTest {
                viewModel.contentToShow.observeForever { }
                whenever(nbaDataSource.getPlayers()) thenReturn successFulResponse
                viewModel.getPlayers()

                viewModel.nameToFilter.value = "Steven"

                val filteredList = listOf(
                    PlayerModel(
                        name = "Steven",
                        surname = "Adams",
                        id = "203500",
                        teamId = "1610612760",
                        jerseyNumber = "12",
                        position = "C"
                    )
                )
                assertEquals(ContentToShow.Success(filteredList), viewModel.contentToShow.value)
            }
        }

        @Test
        fun `given a successful state with a not empty player list WHEN a name that matches no player is entered THEN there should a placeholder state`() {
            runBlockingTest {
                viewModel.contentToShow.observeForever { }
                whenever(nbaDataSource.getPlayers()) thenReturn successFulResponse
                viewModel.getPlayers()

                viewModel.nameToFilter.value = "Carmine"

                assertEquals(ContentToShow.Placeholder, viewModel.contentToShow.value)
            }
        }

        // endregion
        */
    private fun launchFragment() {
        launchFragmentInContainer(
            themeResId = R.style.Base_Theme_MaterialComponents_Light
        ) {
            PlayerListFragment(
                dataSource = nbaDataSource,
                playerDetailPageProvider = playerDetailPageProvider,
                navigator = navigator,
                statsPageProvider = statsPageProvider,
            )
        }
    }
}
