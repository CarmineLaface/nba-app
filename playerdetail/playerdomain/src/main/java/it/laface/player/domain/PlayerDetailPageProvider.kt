package it.laface.player.domain

import it.laface.navigation.Page

fun interface PlayerDetailPageProvider {

    fun getPlayerDetailPage(player: Player): Page
}
