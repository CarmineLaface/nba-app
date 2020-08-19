package it.laface.playerlist

/*@ExperimentalCoroutinesApi
class PlayerListTest {

    @Rule
    @JvmField
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private val nbaDataSource: PlayersDataSource = mock()
    private val playerList: List<PlayerModel> = getPlayerListResponse()
    private val successFulResponse: NetworkResult<List<PlayerModel>> =
        NetworkResult.Success(playerList)
    private val viewModel = PlayerListViewModel(nbaDataSource, Dispatchers.Unconfined)

    private fun errorResponse() = NetworkResult.Error(
        NetworkError.Timeout
    )

    // region -------------------- base tests --------------------

    @Test
    fun `on page first opening WHEN the network call is in progress THEN there should be a loading state`() {
        runBlocking {
            whenever(nbaDataSource.getPlayers()) thenReturn successFulResponse
            val contentList = arrayListOf<ContentToShow>()
            viewModel.contentToShow.observeForever {
                contentList.add(it)
            }

            viewModel.getPlayers()

            contentList.first() shouldBe ContentToShow.Loading
        }
    }

    @Test
    fun `on page first opening WHEN the network call fails then THEN there should be an error state`() {
        runBlockingTest {
            whenever(nbaDataSource.getPlayers()) thenReturn errorResponse()
            viewModel.contentToShow.observeForever { }

            viewModel.getPlayers()

            viewModel.contentToShow.value shouldBe ContentToShow.Error
        }
    }

    @Test
    fun `on page first opening WHEN the network call succeeds then THEN there should be a state with the given list`() {
        runBlockingTest {
            whenever(nbaDataSource.getPlayers()) thenReturn successFulResponse
            viewModel.contentToShow.observeForever { }

            viewModel.getPlayers()

            viewModel.contentToShow.value shouldBe ContentToShow.Success(playerList)
        }
    }

    // endregion

    // region -------------------- retry --------------------

    @Test
    fun `given an error state WHEN a retry action triggers a network call that end successfully THEN the current state should be updated with the given list`() {
        runBlockingTest {
            viewModel.contentToShow.observeForever { }
            whenever(nbaDataSource.getPlayers()) thenReturn errorResponse()
            viewModel.getPlayers()
            whenever(nbaDataSource.getPlayers()) thenReturn successFulResponse

            viewModel.onRetry()

            viewModel.contentToShow.value shouldBe ContentToShow.Success(playerList)
        }
    }

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
}*/
