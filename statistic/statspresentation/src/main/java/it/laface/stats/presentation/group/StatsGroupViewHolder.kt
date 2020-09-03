package it.laface.stats.presentation.group

import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.domain.model.getPlayerImageUrl
import it.laface.stats.domain.StatsSection
import it.laface.stats.presentation.databinding.ItemStatsGroupBinding

class StatsGroupViewHolder(
    private val binding: ItemStatsGroupBinding,
    onItemClicked: (StatsSection) -> Unit
) : BaseViewHolder<StatsSection>(binding.root, onItemClicked) {

    override fun bind(item: StatsSection) {
        super.bind(item)

        binding.nameTextView.text = item.title

        val leader = item.players[0]
        val imageUrl = getPlayerImageUrl(leader.playerId)
        binding.playerImageView.bindImage(imageUrl)
    }
}
