package it.laface.game.presentation

import it.laface.game.domain.GamePageProvider
import it.laface.navigation.Page

val gamePageProvider: GamePageProvider
    get() = GamePageProvider { game ->
        Page(
            fragmentClass = GameFragment::class.java,
            arguments = GameFragment.GAME_KEY to game,
        )
    }
