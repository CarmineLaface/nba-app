package it.laface.team

import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.domain.model.PlayerModel
import it.laface.domain.model.imageUrl
import it.laface.team.databinding.ItemTeamplayerBinding

class PlayerViewHolder(
    private val binding: ItemTeamplayerBinding,
    onItemClicked: (PlayerModel) -> Unit
) : BaseViewHolder<PlayerModel>(binding.root, onItemClicked) {

    override fun bind(item: PlayerModel) {
        super.bind(item)

        binding.nameTextView.text = item.name
        binding.surnameTextView.text = item.surname

        binding.playerImageView.bindImage(item.imageUrl, R.drawable.player_placeholder)
    }
}
