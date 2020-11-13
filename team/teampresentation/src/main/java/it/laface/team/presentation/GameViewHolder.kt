package it.laface.team.presentation

import it.laface.common.util.getCompleteDayName
import it.laface.common.util.toCalendar
import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.domain.model.Team
import it.laface.domain.model.imageUrl
import it.laface.game.domain.Game
import it.laface.team.presentation.databinding.ItemTeamgameBinding
import it.laface.team.presentation.databinding.MiniitemTeamgameBinding

class GameViewHolder(
    private val binding: ItemTeamgameBinding,
    onGameSelected: (Game) -> Unit
) : BaseViewHolder<Game>(binding.root, onGameSelected) {

    override fun bind(item: Game) {
        super.bind(item)

        binding.dateTextView.text = item.date.toCalendar.getCompleteDayName

        binding.homeTeam.bind(item.homeTeam, item.homeScore)
        binding.visitorTeam.bind(item.visitorTeam, item.visitorScore)
    }

    private fun MiniitemTeamgameBinding.bind(item: Team, score: String?) {
        teamCityTextView.text = item.cityName
        teamNameTextView.text = item.nickname
        teamLogoImageView.bindImage(item.imageUrl, R.drawable.circle_grey)
        scoreTextView.text = score ?: "-"
    }
}
