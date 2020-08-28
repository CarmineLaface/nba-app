package it.laface.statistics.detail

import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.domain.model.getPlayerImageUrl
import it.laface.statistics.databinding.ItemLeaderBinding
import it.laface.stats.domain.Leader

class LeadersViewHolder(
    private val binding: ItemLeaderBinding,
    onItemClicked: (it.laface.stats.domain.Leader) -> Unit
) : BaseViewHolder<it.laface.stats.domain.Leader>(binding.root, onItemClicked) {

    override fun bind(item: it.laface.stats.domain.Leader) {
        super.bind(item)

        binding.nameTextView.text = item.playerName
        binding.positionTextView.text = ("#${item.position}")
        binding.customValueTextView.text = item.customValue

        val imageUrl = getPlayerImageUrl(item.playerId)
        binding.playerImageView.bindImage(imageUrl)
    }
}
