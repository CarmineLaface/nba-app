package it.laface.fantanba

import it.laface.game.domain.GamePageProvider
import it.laface.game.presentation.GameFragment
import it.laface.navigation.Page
import it.laface.player.domain.PlayerDetailPageProvider
import it.laface.player.presentation.PlayerDetailFragment
import it.laface.stats.domain.LeadersPageProvider
import it.laface.stats.domain.StatsPageProvider
import it.laface.stats.presentation.detail.LeadersFragment
import it.laface.team.domain.TeamPageProvider
import it.laface.team.presentation.TeamFragment

val actionPlayersToPlayer: PlayerDetailPageProvider
    get() = PlayerDetailPageProvider { player ->
        Page(
            actionResId = R.id.action_players_to_player,
            arguments = PlayerDetailFragment.ARGUMENT_KEY to player
        )
    }

val actionRankingToTeam: TeamPageProvider
    get() = TeamPageProvider { team ->
        Page(
            actionResId = R.id.action_ranking_to_team,
            arguments = TeamFragment.ARGUMENT_KEY to team
        )
    }

val actionTeamToGame: GamePageProvider
    get() = GamePageProvider { game ->
        Page(
            actionResId = R.id.action_team_to_game,
            arguments = GameFragment.GAME_KEY to game
        )
    }

val actionTeamToPlayer: PlayerDetailPageProvider
    get() = PlayerDetailPageProvider { player ->
        Page(
            actionResId = R.id.action_team_to_player,
            arguments = PlayerDetailFragment.ARGUMENT_KEY to player
        )
    }

val actionScheduleToGame: GamePageProvider
    get() = GamePageProvider { game ->
        Page(
            actionResId = R.id.action_schedule_to_game,
            arguments = GameFragment.GAME_KEY to game
        )
    }

val actionPlayerToTeam: TeamPageProvider
    get() = TeamPageProvider { team ->
        Page(
            actionResId = R.id.action_player_to_team,
            arguments = TeamFragment.ARGUMENT_KEY to team
        )
    }

val actionStatsToLeaders: LeadersPageProvider
    get() = LeadersPageProvider { section ->
        Page(
            actionResId = R.id.action_stats_to_leaders,
            arguments = LeadersFragment.STATS_ARG_KEY to section
        )
    }

val actionPlayersToStats: StatsPageProvider
    get() = StatsPageProvider {
        Page(
            actionResId = R.id.action_players_to_stats
        )
    }
