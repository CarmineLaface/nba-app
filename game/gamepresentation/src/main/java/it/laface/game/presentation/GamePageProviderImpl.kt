package it.laface.game.presentation

import it.laface.game.domain.Game
import it.laface.game.domain.GamePageProvider
import it.laface.navigation.Page

object GamePageProviderImpl : GamePageProvider {

    override fun getGamePage(game: Game): Page {
        val fragmentClass = GameFragment::class.java
        val arguments = GameFragment.GAME_KEY to game
        return Page(
            fragmentClass = fragmentClass,
            arguments = arguments,
            tag = fragmentClass.name
        )
    }
}
