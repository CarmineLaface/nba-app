package it.laface.playerlist

import it.laface.base.CallState
import it.laface.domain.model.Player
import it.laface.domain.model.fullName

fun mapContentToShow(
    playerListCallState: CallState<List<Player>>,
    nameToFilter: String
): ContentToShow =
    when (playerListCallState) {
        is CallState.Success -> {
            val filteredList = playerListCallState.result.filter { player ->
                player.fullName.contains(nameToFilter, true)
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
