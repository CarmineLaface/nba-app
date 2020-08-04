package it.laface.ranking

import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.domain.model.RankedTeam
import it.laface.domain.model.fullName
import it.laface.domain.model.imageUrl
import it.laface.ranking.R.drawable
import it.laface.ranking.databinding.ItemTeamBinding

class TeamViewHolder(private val binding: ItemTeamBinding, onItemClicked: (RankedTeam) -> Unit) :
    BaseViewHolder<RankedTeam>(binding.root, onItemClicked) {

    override fun bind(item: RankedTeam) {
        super.bind(item)
        binding.positionTextView.text = item.rankingPosition
        binding.teamNameTextView.text = item.teamInfo.fullName
        binding.winsTextView.text = item.wins
        binding.lossesTextView.text = item.losses
        binding.teamLogoImageView.bindImage(
            item.teamInfo.imageUrl,
            drawable.circle_grey
        )
    }
}
