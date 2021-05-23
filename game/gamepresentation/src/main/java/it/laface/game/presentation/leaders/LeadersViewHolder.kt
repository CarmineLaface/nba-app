package it.laface.game.presentation.leaders

import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.domain.model.getPlayerImageUrl
import it.laface.game.domain.Player
import it.laface.game.presentation.R
import it.laface.game.presentation.databinding.ItemLeadersBinding

class LeadersViewHolder(
    private val binding: ItemLeadersBinding
) : BaseViewHolder<LeaderField>(binding.root) {

    override fun bind(item: LeaderField) {
        binding.titleTextView.text = item.name

        val homePlayer = item.homeLeader.player
        binding.homePlayerImageView.bindImage(homePlayer.imageUrl, R.drawable.player_placeholder)
        binding.homePlayerNameTextView.text = homePlayer.fullName
        binding.homeValueTextView.text = item.homeLeader.value

        val visitorPlayer = item.visitorLeader.player
        binding.visitorPlayerImageView.bindImage(visitorPlayer.imageUrl, R.drawable.player_placeholder)
        binding.visitorPlayerNameTextView.text = visitorPlayer.fullName
        binding.visitorValueTextView.text = item.visitorLeader.value
    }
}

val Player.imageUrl: String
    get() = getPlayerImageUrl(id)

val Player.fullName: String
    get() = "$firstName $lastName"
