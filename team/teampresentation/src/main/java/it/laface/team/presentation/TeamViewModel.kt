package it.laface.team.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.laface.common.ContentListToShow
import it.laface.common.ContentToShow
import it.laface.domain.model.Team
import it.laface.game.domain.Game
import it.laface.game.domain.GamePageProvider
import it.laface.navigation.Navigator
import it.laface.player.domain.Player
import it.laface.player.domain.PlayerDetailPageProvider
import it.laface.team.domain.TeamInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Date

class TeamViewModel(
    val team: Team,
    private val teamDataSourcesManager: TeamDataSourcesManager,
    private val jobDispatcher: CoroutineDispatcher,
    private val navigator: Navigator,
    private val playerPageProvider: PlayerDetailPageProvider,
    private val gamePageProvider: GamePageProvider
) : ViewModel() {

    val rosterContent: Flow<ContentListToShow<Player>> =
        teamDataSourcesManager.rosterCallState.map(::toContent)

    val scheduleContent: Flow<ContentListToShow<Game>> =
        teamDataSourcesManager.scheduleCallState.map(::toContent)

    val teamInfoContent: Flow<ContentToShow<TeamInfo>> =
        teamDataSourcesManager.teamInfoCallState.map(::toContent)

    val openSection: MutableStateFlow<TeamSection> = MutableStateFlow(TeamSection.About)

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
        openSection.value = TeamSection.Roster
    }

    private fun getSchedule() {
        viewModelScope.launch(jobDispatcher) {
            teamDataSourcesManager.loadSchedule(team)
        }
    }

    fun onScheduleClick() {
        openSection.value = TeamSection.Schedule
    }

    private fun getTeamInfo() {
        viewModelScope.launch(jobDispatcher) {
            teamDataSourcesManager.loadInfo(team)
        }
    }

    fun onAboutClick() {
        openSection.value = TeamSection.About
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

    fun onGameSelected(item: Game) {
        val gamePage = gamePageProvider.getGamePage(item)
        navigator.navigateForward(gamePage)
    }
}
