package it.laface.playerlist

import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.player.domain.Player
import it.laface.player.domain.fullName
import it.laface.player.domain.imageUrl
import it.laface.playerlist.databinding.ItemPlayerBinding

class PlayerViewHolder(
    private val binding: ItemPlayerBinding,
    onItemClicked: (Player) -> Unit
) : BaseViewHolder<Player>(binding.root, onItemClicked) {

    override fun bind(item: Player) {
        super.bind(item)

        binding.nameTextView.text = item.fullName

        binding.playerImageView.bindImage(item.imageUrl, R.drawable.player_placeholder)
    }
}
