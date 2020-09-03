package it.laface.team

import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.player.domain.Player
import it.laface.player.domain.imageUrl
import it.laface.team.databinding.ItemTeamplayerBinding

class PlayerViewHolder(
    private val binding: ItemTeamplayerBinding,
    onItemClicked: (Player) -> Unit
) : BaseViewHolder<Player>(binding.root, onItemClicked) {

    override fun bind(item: Player) {
        super.bind(item)

        binding.nameTextView.text = item.name
        binding.surnameTextView.text = item.surname

        binding.playerImageView.bindImage(item.imageUrl, R.drawable.player_placeholder)
    }
}
