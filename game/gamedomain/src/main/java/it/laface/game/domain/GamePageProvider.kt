package it.laface.game.domain

import it.laface.navigation.Page

fun interface GamePageProvider {

    fun getGamePage(game: Game): Page
}
