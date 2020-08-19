package it.laface.playerlist

import it.laface.domain.model.PlayerModel
import it.laface.domain.model.fullName
import it.laface.domain.network.CallState

fun mapContentToShow(
    playerListCallState: CallState<List<PlayerModel>>,
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
