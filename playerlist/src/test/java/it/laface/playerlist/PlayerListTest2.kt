package it.laface.playerlist

import org.hamcrest.CoreMatchers.not

/*@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
@LooperMode(LooperMode.Mode.PAUSED)
class PlayerListTest2 {

    private val nbaDataSource: NbaServices = mock()
    private val playerListResponse = getPlayerListResponse()
    private val playerList = playerListResponse.league.standard.map(::toDomain)
    private val successFulResponse: Response<PlayerListResponse> =
        successfulResponse(playerListResponse)

    private fun getPlayerListFragment(): FragmentScenario<PlayerListFragment> {
        return launchFragmentInContainer<PlayerListFragment>(themeResId = R.style.Theme_MaterialComponents_DayNight_NoActionBar) { PlayerListFragment(FakeDataSource2(successFulResponse)) }
    }

    @Test
    fun `on page first opening WHEN the network call succeeds then THEN there should be a state with the given list`() {

        val fragment = getPlayerListFragment()

        onView(withId(R.id.playerNameEditText)).check(matches(isDisplayed()))
    }
}

class FakeDataSource2(private val response: Response<PlayerListResponse>) : NbaServices {
    override suspend fun teamConfig(year: String): Response<TeamConfigurationResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun playerList(year: String): Response<PlayerListResponse> = response

    override suspend fun ranking(): Response<RankingResponse> {
        TODO("Not yet implemented")
    }
}*/
