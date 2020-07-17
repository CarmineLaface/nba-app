package it.laface.ranking

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.laface.common.view.BaseAdapter
import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.common.view.inflater
import it.laface.domain.model.RankedTeam
import it.laface.domain.model.imageUrl
import kotlinx.android.synthetic.main.item_team.view.*

class RankingViewHolder(private val contentView: RecyclerView) :
    BaseViewHolder<List<RankedTeam>>(contentView, {}) {

    init {
        contentView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        contentView.layoutManager = LinearLayoutManager(contentView.context)
        contentView.adapter = BaseAdapter { parent ->
            TeamViewHolder(
                parent.inflater.inflate(R.layout.item_team, parent, false)
            )
        }
    }

    override fun bind(item: List<RankedTeam>) {
        contentView.setList(item)
    }
}

class TeamViewHolder(itemView: View) : BaseViewHolder<RankedTeam>(itemView, {}) {

    override fun bind(item: RankedTeam) {
        itemView.tv_position.text = item.rankingPosition
        itemView.tv_name.text = item.name
        itemView.iv_photo.bindImage(item.imageUrl, R.drawable.circle_grey)
    }
}
