package it.laface.schedule

import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.domain.model.Game
import it.laface.domain.model.fullName
import it.laface.domain.model.imageUrl
import it.laface.schedule.databinding.ItemGameBinding

class GameViewHolder(
    private val binding: ItemGameBinding
) : BaseViewHolder<Game>(binding.root) {

    override fun bind(item: Game) {
        binding.homeTeamNameTextView.text = item.homeTeam.fullName
        binding.homeTeamLogoImageView.bindImage(item.homeTeam.imageUrl, R.drawable.circle_grey)

        binding.visitorTeamNameTextView.text = item.visitorTeam.fullName
        binding.visitorTeamLogoImageView.bindImage(item.visitorTeam.imageUrl, R.drawable.circle_grey)
    }
}
