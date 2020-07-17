package it.laface.ranking

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.laface.common.view.BaseAdapter
import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.common.view.inflater
import it.laface.domain.model.RankedTeam
import it.laface.domain.model.imageUrl
import it.laface.ranking.databinding.ItemTeamBinding

class RankingViewHolder(contentView: RecyclerView) : BaseViewHolder<List<RankedTeam>>(contentView) {

    private val adapter: BaseAdapter<RankedTeam> = BaseAdapter { parent ->
        TeamViewHolder(
            ItemTeamBinding.inflate(parent.inflater, parent, false)
        )
    }

    init {
        contentView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        contentView.layoutManager = LinearLayoutManager(contentView.context)
        contentView.adapter = adapter
    }

    override fun bind(item: List<RankedTeam>) {
        adapter.list = item
    }
}

class TeamViewHolder(private val binding: ItemTeamBinding) :
    BaseViewHolder<RankedTeam>(binding.root) {

    override fun bind(item: RankedTeam) {
        binding.positionTextView.text = item.rankingPosition
        binding.teamNameTextView.text = item.name
        binding.teamLogoImageView.bindImage(item.imageUrl, R.drawable.circle_grey)
    }
}