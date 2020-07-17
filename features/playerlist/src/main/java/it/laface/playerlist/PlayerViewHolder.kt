package it.laface.playerlist

import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.domain.model.PlayerModel
import it.laface.domain.model.fullName
import it.laface.domain.model.imageUrl
import it.laface.playerlist.databinding.ItemPlayerBinding

class PlayerViewHolder(
    private val binding: ItemPlayerBinding
) : BaseViewHolder<PlayerModel>(binding.root, {}) {

    override fun bind(item: PlayerModel) {
        super.bind(item)

        binding.nameTextView.text = item.fullName

        binding.playerImageView.bindImage(item.imageUrl, R.drawable.player_placeholder)
    }
}
