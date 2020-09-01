package it.laface.player.domain

import it.laface.domain.model.Player
import it.laface.navigation.Page

interface PlayerDetailPageProvider {

    fun getPlayerDetailPage(player: Player): Page
}
