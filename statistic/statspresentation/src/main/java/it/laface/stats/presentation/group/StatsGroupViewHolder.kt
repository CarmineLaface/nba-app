package it.laface.stats.presentation.group

import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.domain.model.getPlayerImageUrl
import it.laface.stats.domain.StatsSection
import it.laface.stats.presentation.databinding.ItemStatsGroupBinding

class StatsGroupViewHolder(
    private val binding: ItemStatsGroupBinding,
    private val onItemClicked: (StatsSection) -> Unit
) : BaseViewHolder<StatsSection>(binding.root) {

    override fun bind(item: StatsSection) {
        itemView.setOnClickListener { onItemClicked.invoke(item) }

        binding.nameTextView.text = item.title

        val leader = item.players[0]
        val imageUrl = getPlayerImageUrl(leader.playerId)
        binding.playerImageView.bindImage(imageUrl)
    }
}
