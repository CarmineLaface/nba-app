package it.laface.game.presentation

import it.laface.common.view.BaseViewHolder
import it.laface.game.presentation.databinding.ItemLineScoreBinding

class LineScoreViewHolder(
    private val binding: ItemLineScoreBinding
) : BaseViewHolder<Pair<String, String>>(binding.root) {

    override fun bind(item: Pair<String, String>) {
        binding.homeTeamLinescore.text = item.first
        binding.visitorTeamLinescore.text = item.second
    }
}
