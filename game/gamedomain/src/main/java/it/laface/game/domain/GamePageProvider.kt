package it.laface.game.domain

import it.laface.navigation.Page

interface GamePageProvider {

    fun getGamePage(game: Game): Page
}
