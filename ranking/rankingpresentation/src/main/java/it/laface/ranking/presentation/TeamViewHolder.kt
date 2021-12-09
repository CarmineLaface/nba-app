package it.laface.ranking.presentation

import it.laface.common.R
import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.domain.model.fullName
import it.laface.domain.model.imageUrl
import it.laface.ranking.domain.RankedTeam
import it.laface.ranking.presentation.databinding.ItemTeamBinding

class TeamViewHolder(
    private val binding: ItemTeamBinding,
    private val onItemClicked: (RankedTeam) -> Unit
) : BaseViewHolder<RankedTeam>(binding.root) {

    override fun bind(item: RankedTeam) {
        itemView.setOnClickListener { onItemClicked.invoke(item) }
        binding.positionTextView.text = item.rankingPosition
        binding.teamNameTextView.text = item.teamInfo.fullName
        binding.winsTextView.text = item.wins
        binding.lossesTextView.text = item.losses
        binding.teamLogoImageView.bindImage(
            item.teamInfo.imageUrl,
            R.drawable.circle_grey
        )
    }
}
