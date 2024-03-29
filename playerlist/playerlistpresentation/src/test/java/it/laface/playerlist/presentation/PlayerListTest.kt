@file:Suppress("MaxLineLength")

package it.laface.playerlist.presentation
/*
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaListAssertions.assertListItemCount
import com.adevinta.android.barista.assertion.BaristaListAssertions.assertListNotEmpty
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaEditTextInteractions.writeTo
import it.laface.base.NetworkError.UnknownError
import it.laface.base.NetworkResult
import it.laface.base.NetworkResult.Failure
import it.laface.navigation.Navigator
import it.laface.player.domain.Player
import it.laface.player.domain.PlayerDetailPageProvider
import it.laface.player.domain.PlayersDataSource
import it.laface.stats.domain.StatsPageProvider
import it.laface.test.mock
import it.laface.test.thenAnswer
import it.laface.test.whenever
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import com.google.android.material.R as MR

@RunWith(AndroidJUnit4::class)
@Suppress("MaxLineLength")
class PlayerListTest {

    private val nbaDataSource: PlayersDataSource = mock()
    private val playerDetailPageProvider: PlayerDetailPageProvider = mock()
    private val navigator: Navigator = mock()
    private val statsPageProvider: StatsPageProvider = mock()
    private val errorResponse = Failure(UnknownError("error"))

    // region -------------------- base tests --------------------

    @Test
    fun `on page first opening WHEN the network call is in progress THEN there should be a loading state`() {
        runBlocking {
            val sleepyDataSource: PlayersDataSource = object : PlayersDataSource {
                override suspend fun getPlayers(): NetworkResult<List<Player>> {
                    delay(5000)
                    return successFulResponse
                }
            }
            launchFragment(sleepyDataSource)

            assertNotDisplayed(R.id.playersRecyclerView)
            assertNotDisplayed(R.id.emptyListPlaceholder)
            assertNotDisplayed(R.id.retryButton)
            assertDisplayed(R.id.progressBar)
        }
    }

    @Test
    fun `on page first opening WHEN the network call fails then THEN there should be an error state`() {
        runBlocking {
            whenever(nbaDataSource.getPlayers()) thenAnswer { errorResponse }
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
            whenever(nbaDataSource.getPlayers()) thenAnswer { successFulResponse }
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
            whenever(nbaDataSource.getPlayers()) thenAnswer { errorResponse }
            launchFragment()
            whenever(nbaDataSource.getPlayers()) thenAnswer { successFulResponse }

            clickOn(R.id.retryButton)

            assertListNotEmpty(R.id.playersRecyclerView)
            assertNotDisplayed(R.id.retryButton)
        }
    }

    @Test
    fun `given an error state WHEN a retry action triggers a network call that fails THEN the current state should be error`() {
        runBlocking {
            whenever(nbaDataSource.getPlayers()) thenAnswer { errorResponse }
            launchFragment()

            clickOn(R.id.retryButton)

            assertNotDisplayed(R.id.playersRecyclerView)
            assertDisplayed(R.id.retryButton)
        }
    }

    // endregion

    // region -------------------- filter for name --------------------

    @Test
    fun `given a successful state with a not empty player list WHEN a name is entered THEN there should be a state with a list filtered by that name`() {
        runBlocking {
            whenever(nbaDataSource.getPlayers()) thenAnswer { successFulResponse }
            launchFragment()

            writeTo(R.id.playerNameEditText, "Steven")

            assertListItemCount(R.id.playersRecyclerView, expectedItemCount = 1)
        }
    }

    @Test
    fun `given a successful state with a not empty player list WHEN a name that matches no player is entered THEN there should a placeholder state`() {
        runBlocking {
            whenever(nbaDataSource.getPlayers()) thenAnswer { successFulResponse }
            launchFragment()

            writeTo(R.id.playerNameEditText, "Carmine")

            assertDisplayed(R.id.emptyListPlaceholder)
        }
    }

    // endregion

    private fun launchFragment(dataSource: PlayersDataSource = nbaDataSource) {
        launchFragmentInContainer(
            themeResId = MR.style.Base_Theme_MaterialComponents_Light
        ) {
            PlayerListFragment(
                dataSource = dataSource,
                playerDetailPageProvider = playerDetailPageProvider,
                navigator = navigator,
                statsPageProvider = statsPageProvider,
            )
        }
    }
}
*/
