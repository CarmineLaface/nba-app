package it.laface.domain.navigation

import it.laface.domain.model.PlayerModel

interface PlayerDetailNavigationProvider {

    fun navigateToPlayerDetail(player: PlayerModel)
}
