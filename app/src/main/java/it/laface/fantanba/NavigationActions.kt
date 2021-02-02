package it.laface.fantanba

import it.laface.game.domain.GamePageProvider
import it.laface.game.presentation.GameFragment
import it.laface.navigation.Page
import it.laface.player.domain.PlayerDetailPageProvider
import it.laface.player.presentation.PlayerDetailFragment
import it.laface.team.domain.TeamPageProvider
import it.laface.team.presentation.TeamFragment

val actionPlayersToPlayer = PlayerDetailPageProvider { player ->
    Page(
        actionResId = R.id.action_players_to_player,
        arguments = PlayerDetailFragment.ARGUMENT_KEY to player
    )
}

val actionRankingToTeam = TeamPageProvider { team ->
    Page(
        actionResId = R.id.action_ranking_to_team,
        arguments = TeamFragment.ARGUMENT_KEY to team
    )
}

val actionTeamToGame = GamePageProvider { game ->
    Page(
        actionResId = R.id.action_team_to_game,
        arguments = GameFragment.GAME_KEY to game
    )
}

val actionTeamToPlayer = PlayerDetailPageProvider { player ->
    Page(
        actionResId = R.id.action_team_to_player,
        arguments = PlayerDetailFragment.ARGUMENT_KEY to player
    )
}

val actionScheduleToGame = GamePageProvider { game ->
    Page(
        actionResId = R.id.action_schedule_to_game,
        arguments = GameFragment.GAME_KEY to game
    )
}