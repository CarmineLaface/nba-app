package it.laface.playerdetail

import androidx.lifecycle.ViewModel
import it.laface.domain.NetworkResult
import it.laface.domain.datasource.TeamRepository
import it.laface.domain.model.PlayerModel
import it.laface.domain.model.RankedTeam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlayerDetailViewModel(
    val player: PlayerModel,
    private val teamRepository: TeamRepository
) : ViewModel() {

    val team: Flow<RankedTeam> = flow {
        val result = teamRepository.getTeam(player.teamId)
        if (result is NetworkResult.Success) {
            emit(result.value)
        }
    }
}