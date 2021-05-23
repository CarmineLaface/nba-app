package it.laface.team.presentation

import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.player.domain.Player
import it.laface.player.domain.imageUrl
import it.laface.team.presentation.databinding.ItemTeamplayerBinding

class PlayerViewHolder(
    private val binding: ItemTeamplayerBinding,
    private val onItemClicked: (Player) -> Unit
) : BaseViewHolder<Player>(binding.root) {

    override fun bind(item: Player) {
        itemView.setOnClickListener { onItemClicked.invoke(item) }

        binding.nameTextView.text = item.name
        binding.surnameTextView.text = item.surname
        binding.positionTextView.text = item.position
        binding.jerseyTextView.text = item.jerseyNumber

        binding.playerImageView.bindImage(item.imageUrl, R.drawable.player_placeholder)
    }
}
