package it.laface.ranking

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import it.laface.common.view.BaseAdapter
import it.laface.common.view.BaseViewHolder
import it.laface.common.view.inflater
import it.laface.domain.RankedTeam
import it.laface.domain.imageUrl
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
        Glide.with(itemView.context)
            .load(item.imageUrl)
            .apply(RequestOptions.placeholderOf(R.drawable.circle_grey))
            .into(itemView.iv_photo)
    }
}
