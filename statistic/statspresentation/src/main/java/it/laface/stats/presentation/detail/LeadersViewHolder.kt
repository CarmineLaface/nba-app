package it.laface.stats.presentation.detail

import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.domain.model.getPlayerImageUrl
import it.laface.stats.domain.Leader
import it.laface.stats.presentation.databinding.ItemLeaderBinding

class LeadersViewHolder(
    private val binding: ItemLeaderBinding,
    private val onItemClicked: (Leader) -> Unit
) : BaseViewHolder<Leader>(binding.root) {

    override fun bind(item: Leader) {
        itemView.setOnClickListener { onItemClicked.invoke(item) }

        binding.nameTextView.text = item.playerName
        binding.positionTextView.text = ("#${item.rank}")
        binding.customValueTextView.text = item.customValue

        val imageUrl = getPlayerImageUrl(item.playerId)
        binding.playerImageView.bindImage(imageUrl)
    }
}
