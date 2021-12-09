package it.laface.playerlist.presentation

import it.laface.common.R
import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.player.domain.Player
import it.laface.player.domain.fullName
import it.laface.player.domain.imageUrl
import it.laface.playerlist.presentation.databinding.ItemPlayerBinding

class PlayerViewHolder(
    private val binding: ItemPlayerBinding,
    private val onItemClicked: (Player) -> Unit
) : BaseViewHolder<Player>(binding.root) {

    override fun bind(item: Player) {
        itemView.setOnClickListener { onItemClicked.invoke(item) }

        binding.nameTextView.text = item.fullName

        binding.playerImageView.bindImage(item.imageUrl, R.drawable.player_placeholder)
    }
}
