package it.laface.playerdetail

import androidx.lifecycle.ViewModel
import it.laface.domain.datasource.TeamRepository
import it.laface.domain.model.NbaTeam
import it.laface.domain.model.PlayerModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlayerDetailViewModel(
    val player: PlayerModel,
    private val teamRepository: TeamRepository
) : ViewModel() {

    val team: Flow<NbaTeam> = flow {
        emit(teamRepository.getTeam(player.teamId))
    }
}
