package it.laface.domain.navigation

import it.laface.domain.model.PlayerModel

interface PlayerDetailPageProvider {

    fun getPlayerDetailPage(player: PlayerModel): Page
}
