package it.laface.playerdetail

import androidx.lifecycle.ViewModel
import it.laface.domain.model.NbaTeam
import it.laface.domain.model.PlayerModel
import it.laface.navigation.Navigator
import it.laface.team.domain.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlayerDetailViewModel(
    val player: PlayerModel,
    private val teamRepository: TeamRepository,
    private val navigator: Navigator
) : ViewModel() {

    val team: Flow<NbaTeam> = flow {
        emit(teamRepository.getTeam(player.teamId))
    }

    fun goBack() {
        navigator.navigateBack()
    }

    fun navigateToTeamPage() {
        TODO()
    }
}
