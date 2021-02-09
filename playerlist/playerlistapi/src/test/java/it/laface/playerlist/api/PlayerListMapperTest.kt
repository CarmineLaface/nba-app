package it.laface.playerlist.api

import io.mockk.coEvery
import io.mockk.mockk
import it.laface.base.NetworkResult
import it.laface.player.domain.Player
import it.laface.test.errorResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PlayerListMapperTest {

    private val service: PlayerListService = mockk()
    private val successfulResult = NetworkResult.Success(
        listOf(
            Player(
                name="Precious",
                surname="Achiuwa",
                id="1630173",
                teamId="1610612748",
                jerseyNumber="5",
                position="F"
            ),
            Player(
                name="Jaylen",
                surname="Adams",
                id="1629121",
                teamId="1610612749",
                jerseyNumber="6",
                position="G"
            ),
            Player(
                name="Steven",
                surname="Adams",
                id="203500",
                teamId="1610612740",
                jerseyNumber="12",
                position="C"
            ),
            Player(
                name="Bam",
                surname="Adebayo",
                id="1628389",
                teamId="1610612748",
                jerseyNumber="13",
                position="C-F"
            ),
            Player(
                name="LaMarcus",
                surname="Aldridge",
                id="200746",
                teamId="1610612759",
                jerseyNumber="12",
                position="C-F"
            ),
            Player(
                name="Ty-Shon",
                surname="Alexander",
                id="1630234",
                teamId="1610612756",
                jerseyNumber="0",
                position="G"
            ),
            Player(
                name="Nickeil",
                surname="Alexander-Walker",
                id="1629638",
                teamId="1610612740",
                jerseyNumber="6",
                position="G"
            ),
            Player(
                name="Grayson",
                surname="Allen",
                id="1628960",
                teamId="1610612763",
                jerseyNumber="3",
                position="G"
            ),
        )
    )

    @Test
    fun `calling data source WHEN no errors occur THEN return successful response`() {
        runBlocking {
            coEvery { service.playerList() } returns getSuccessfulResponse()
            val mapper = PlayerListMapper(service)

            val response = mapper.getPlayers()

            assertEquals(successfulResult, response)
        }
    }

    @Test
    fun `calling data source WHEN errors occur THEN return error response`() {
        runBlocking {
            coEvery { service.playerList() } returns errorResponse()
            val mapper = PlayerListMapper(service)

            val response = mapper.getPlayers()

            assertTrue(response is NetworkResult.Failure)
        }
    }
}