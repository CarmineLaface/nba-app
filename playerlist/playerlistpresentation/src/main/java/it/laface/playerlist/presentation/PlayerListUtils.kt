package it.laface.playerlist.presentation

import it.laface.base.CallState
import it.laface.common.ContentListToShow
import it.laface.common.ContentToShow
import it.laface.player.domain.Player
import it.laface.player.domain.Position
import it.laface.player.domain.fullName

fun mapContentToShow(
    playerListCallState: CallState<List<Player>>,
    nameToFilter: String,
    positions: Set<Position>
): ContentListToShow<Player> =
    when (playerListCallState) {
        is CallState.Success -> {
            val positionsValue: List<String> = positions.map(Position::value)
            val filteredList = playerListCallState.result
                .filter { player ->
                    player.fullName.contains(nameToFilter, true) &&
                        player.contains(positionsValue)
                }
            if (filteredList.isEmpty()) {
                ContentToShow.Placeholder
            } else {
                ContentToShow.Success(filteredList)
            }
        }
        is CallState.Error -> ContentToShow.Error
        CallState.InProgress, CallState.NotStarted -> ContentToShow.Loading
    }

private fun Player.contains(positions: List<String>): Boolean =
    positions.all { position.contains(it) }
