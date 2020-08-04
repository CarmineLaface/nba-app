package it.laface.statistics

import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.domain.model.StatsSection
import it.laface.domain.model.getPlayerImageUrl
import it.laface.statistics.databinding.ItemStatisticBinding

class StatsGroupViewHolder(
    private val binding: ItemStatisticBinding,
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
