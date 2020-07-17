package it.laface.schedule

import it.laface.common.view.BaseViewHolder
import it.laface.domain.model.Game
import it.laface.schedule.databinding.ItemGameBinding

class GameViewHolder(
    private val binding: ItemGameBinding
) : BaseViewHolder<Game>(binding.root) {

    override fun bind(item: Game) {
        binding.homeTextView.text = item.homeTeam.id
        binding.visitorTextView.text = item.visitorTeam.id
    }
}