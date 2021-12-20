package it.laface.news.presentation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaListAssertions.assertListNotEmpty
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import it.laface.base.NetworkError
import it.laface.base.NetworkResult
import it.laface.navigation.MessageEmitter
import it.laface.navigation.SnackbarInfo
import it.laface.news.domain.Article
import it.laface.news.domain.BrowserProvider
import it.laface.news.domain.NewsDataSource
import it.laface.test.mock
import it.laface.test.thenAnswer
import it.laface.test.whenever
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import com.google.android.material.R as MR
import it.laface.common.R as CR

@RunWith(AndroidJUnit4::class)
@Suppress("MaxLineLength")
class NewsFragmentTest {

    private val newsDataSource: NewsDataSource = mock()
    private val browserProvider: BrowserProvider = mock()
    private val messageEmitter: MessageEmitter = mock {
        whenever(show(SnackbarInfo(CR.string.error))) thenAnswer {}
    }
    private val errorResponse =
        NetworkResult.Failure(NetworkError.UnknownError("error"))

    // region -------------------- base tests --------------------

    @Test
    fun `on page first opening WHEN the network call is in progress THEN there should be a loading state`() {
        runBlocking {
            val sleepyDataSource: NewsDataSource = object : NewsDataSource {
                override suspend fun getNews(): NetworkResult<List<Article>> {
                    delay(5000)
                    return successFulResponse
                }
            }
            launchFragment(sleepyDataSource)

            assertNotDisplayed(R.id.newsRecyclerView)
            assertNotDisplayed(R.id.retryButton)
            assertDisplayed(R.id.progressBar)
        }
    }

    @Test
    fun `on page first opening WHEN the network call fails then THEN there should be an error state`() {
        runBlocking {
            whenever(newsDataSource.getNews()) thenAnswer { errorResponse }
            launchFragment()

            assertNotDisplayed(R.id.newsRecyclerView)
            assertDisplayed(R.id.retryButton)
            assertNotDisplayed(R.id.progressBar)
        }
    }

    @Test
    fun `on page first opening WHEN the network call succeeds then THEN there should be a state with the given list`() {
        runBlocking {
            whenever(newsDataSource.getNews()) thenAnswer { successFulResponse }
            launchFragment()

            assertListNotEmpty(R.id.newsRecyclerView)
            assertNotDisplayed(R.id.retryButton)
            assertNotDisplayed(R.id.progressBar)
        }
    }

    // endregion

    // region -------------------- retry --------------------

    @Test
    fun `given an error state WHEN a retry action triggers a network call that end successfully THEN the current state should be updated with the given list`() {
        runBlocking {
            whenever(newsDataSource.getNews()) thenAnswer { errorResponse }
            launchFragment()
            whenever(newsDataSource.getNews()) thenAnswer { successFulResponse }

            clickOn(R.id.retryButton)

            assertListNotEmpty(R.id.newsRecyclerView)
            assertNotDisplayed(R.id.retryButton)
        }
    }

    @Test
    fun `given an error state WHEN a retry action triggers a network call that fails THEN the current state should be error`() {
        runBlocking {
            whenever(newsDataSource.getNews()) thenAnswer { errorResponse }
            launchFragment()

            clickOn(R.id.retryButton)

            assertNotDisplayed(R.id.newsRecyclerView)
            assertDisplayed(R.id.retryButton)
        }
    }

    /*
    @Test
    fun `given an error state WHEN a refresh action triggers a network call that fails THEN the current state should be error and a message emitted`() {
        runBlocking {
            whenever(newsDataSource.getNews() ) doAnswer {errorResponse}
            launchFragment()
            delay(5000)

            refresh(R.id.swipeRefreshLayout)

            assertNotDisplayed(R.id.newsRecyclerView)
            assertDisplayed(R.id.retryButton)
            verify { messageEmitter.show(SnackbarInfo(R.string.error)) }
        }
    }
    */

    // endregion

    private fun launchFragment(dataSource: NewsDataSource = newsDataSource) {
        launchFragmentInContainer(
            themeResId = MR.style.Base_Theme_MaterialComponents_Light
        ) {
            NewsFragment(
                dataSource = dataSource,
                browserProvider = browserProvider,
                messageEmitter = messageEmitter,
            )
        }
    }
}
