package it.laface.team.presentation

import it.laface.common.util.getCompleteDayName
import it.laface.common.util.toCalendar
import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.domain.model.imageUrl
import it.laface.game.domain.Game
import it.laface.team.presentation.databinding.ItemTeamgameBinding

class GameViewHolder(
    private val binding: ItemTeamgameBinding,
    private val onGameSelected: (Game) -> Unit
) : BaseViewHolder<Game>(binding.root) {

    override fun bind(item: Game) {
        itemView.setOnClickListener { onGameSelected.invoke(item) }

        binding.dateTextView.text = item.date.toCalendar.getCompleteDayName

        binding.homeTeamNameTextView.text = item.homeTeam.nickname
        binding.homeTeamLogoImageView.bindImage(item.homeTeam.imageUrl, R.drawable.circle_grey)
        binding.homeScoreTextView.text = item.homeScore ?: "-"

        binding.visitorTeamNameTextView.text = item.visitorTeam.nickname
        binding.visitorTeamLogoImageView.bindImage(item.visitorTeam.imageUrl, R.drawable.circle_grey)
        binding.visitorScoreTextView.text = item.visitorScore ?: "-"
    }
}
