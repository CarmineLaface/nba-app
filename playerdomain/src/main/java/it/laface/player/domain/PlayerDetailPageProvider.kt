package it.laface.player.domain

import it.laface.domain.model.PlayerModel
import it.laface.navigation.Page

interface PlayerDetailPageProvider {

    fun getPlayerDetailPage(player: PlayerModel): Page
}
