package it.laface.playerlist

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import it.laface.api.NbaServices
import it.laface.api.models.PlayerListResponse
import it.laface.api.models.RankingResponse
import it.laface.api.models.TeamConfigurationResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response
import java.io.InputStreamReader
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class PlayerListTest3 {

    // private val nbaDataSource: NbaServices = mock()
    private val playerListResponse = getPlayerListResponse()
    private val playerList = playerListResponse.league.standard.map(::toDomain)
    private val successFulResponse: Response<PlayerListResponse> =
        successfulResponse(playerListResponse) // Theme.MaterialComponents.DayNight.NoActionBar

    private fun getPlayerListFragment(nbaDataSource: NbaServices): FragmentScenario<PlayerListFragment> {
        return launchFragmentInContainer<PlayerListFragment>(themeResId = R.style.Theme_MaterialComponents_DayNight_NoActionBar) { PlayerListFragment(nbaDataSource) }
    }

    private fun Any.openResources(fileName: String): InputStreamReader =
        InputStreamReader(javaClass.classLoader!!.getResourceAsStream(fileName))

    private fun Any.getPlayerListResponse(): PlayerListResponse {
        return Gson().fromJson(openResources("players.json"), PlayerListResponse::class.java)
    }

    private fun <T> successfulResponse(contentBody: T): Response<T> =
        Response.success(contentBody)

    private fun <T> errorResponse(httpCode: Int = HttpURLConnection.HTTP_INTERNAL_ERROR, jsonErrorBody: String = "error"): Response<T> =
        Response.error(
            httpCode,
            jsonErrorBody.toResponseBody("application/json".toMediaType())
        )

    @Test
    fun prova() {
        val fragment = getPlayerListFragment(FakeDataSource(successFulResponse))

        onView(withId(R.id.playerNameEditText)).check(matches(isDisplayed()))
    }
}

class FakeDataSource(private val response: Response<PlayerListResponse>) : NbaServices {
    override suspend fun teamConfig(year: String): Response<TeamConfigurationResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun playerList(year: String): Response<PlayerListResponse> = response

    override suspend fun ranking(): Response<RankingResponse> {
        TODO("Not yet implemented")
    }
}
