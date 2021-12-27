package it.laface.schedule.presentation

import it.laface.common.R
import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.domain.model.imageUrl
import it.laface.game.domain.Game
import it.laface.schedule.presentation.databinding.ItemGameBinding

class GameViewHolder(
    private val binding: ItemGameBinding,
    private val onGameSelected: (Game) -> Unit
) : BaseViewHolder<Game>(binding.root) {

    override fun bind(item: Game) {
        itemView.setOnClickListener { onGameSelected.invoke(item) }

        binding.homeTeamLogoImageView.bindImage(item.homeTeam.imageUrl, R.drawable.circle_grey)
        binding.homeScoreTextView.text = item.homeScore ?: "-"

        binding.visitorTeamLogoImageView.bindImage(item.visitorTeam.imageUrl, R.drawable.circle_grey)
        binding.visitorScoreTextView.text = item.visitorScore ?: "-"
    }
}
