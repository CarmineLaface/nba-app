package it.laface.team.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.common.ContentListToShow
import it.laface.common.ContentToShow
import it.laface.domain.model.Team
import it.laface.navigation.Navigator
import it.laface.player.domain.Player
import it.laface.player.domain.PlayerDetailPageProvider
import it.laface.schedule.domain.Game
import it.laface.team.domain.TeamInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Date

@Suppress("EXPERIMENTAL_API_USAGE")
class TeamViewModel(
    val team: Team,
    private val teamDataSourcesManager: TeamDataSourcesManager,
    private val jobDispatcher: CoroutineDispatcher,
    private val navigator: Navigator,
    private val playerPageProvider: PlayerDetailPageProvider
) : ViewModel() {

    val isRosterOpen: MutableStateFlow<Boolean> = MutableStateFlow(value = true)
    val rosterContent: Flow<ContentListToShow<Player>> =
        teamDataSourcesManager.rosterCallState.map(::toContent)

    val isScheduleOpen: MutableStateFlow<Boolean> = MutableStateFlow(value = true)
    val scheduleContent: Flow<ContentListToShow<Game>> =
        teamDataSourcesManager.scheduleCallState.map(::toContent)

    val isAboutOpen: MutableStateFlow<Boolean> = MutableStateFlow(value = true)
    val teamInfoContent: Flow<ContentToShow<TeamInfo>> =
        teamDataSourcesManager.teamInfoCallState.map(::toContent)

    init {
        getRoster()
        getSchedule()
        getTeamInfo()
    }

    private fun getRoster() {
        viewModelScope.launch(jobDispatcher) {
            teamDataSourcesManager.loadRoster(team)
        }
    }

    fun onRosterClick() {
        isRosterOpen.value = isRosterOpen.value.not()
    }

    private fun getSchedule() {
        viewModelScope.launch(jobDispatcher) {
            teamDataSourcesManager.loadSchedule(team)
        }
    }

    fun onScheduleClick() {
        isScheduleOpen.value = isScheduleOpen.value.not()
    }

    private fun getTeamInfo() {
        viewModelScope.launch(jobDispatcher) {
            teamDataSourcesManager.loadInfo(team)
        }
    }

    fun onAboutClick() {
        isAboutOpen.value = isAboutOpen.value.not()
    }

    fun playerSelected(player: Player) {
        navigator.navigateForward(playerPageProvider.getPlayerDetailPage(player))
    }

    fun scrollScheduleToIndex(schedule: List<Game>): Int {
        val today = Date()
        return schedule.indexOfFirst { it.date.after(today) }
    }

    fun navigateBack() {
        navigator.navigateBack()
    }
}
