package it.laface.playerdetail

import androidx.lifecycle.ViewModel
import it.laface.domain.model.Player
import it.laface.domain.model.Team
import it.laface.navigation.Navigator
import it.laface.team.domain.TeamPageProvider
import it.laface.team.domain.TeamRepository

class PlayerDetailViewModel(
    val player: Player,
    teamRepository: TeamRepository,
    private val navigator: Navigator,
    private val teamPageProvider: TeamPageProvider
) : ViewModel() {

    val team: Team = teamRepository.getTeam(player.teamId)

    fun goBack() {
        navigator.navigateBack()
    }

    fun navigateToTeamPage() {
        navigator.navigateForward(teamPageProvider.getTeamPage(team))
    }
}
