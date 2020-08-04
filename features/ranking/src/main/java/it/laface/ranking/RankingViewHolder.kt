package it.laface.ranking

import android.view.ViewGroup
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.laface.common.view.BaseAdapter
import it.laface.common.view.BaseViewHolder
import it.laface.common.view.inflater
import it.laface.domain.model.RankedTeam
import it.laface.ranking.databinding.ItemTeamBinding

class RankingViewHolder(contentView: RecyclerView, onTeamClicked: (RankedTeam) -> Unit) :
    BaseViewHolder<List<RankedTeam>>(contentView) {

    private val adapter: BaseAdapter<RankedTeam> = BaseAdapter { parent ->
        TeamViewHolder(
            ItemTeamBinding.inflate(parent.inflater, parent, false),
            onTeamClicked
        )
    }

    init {
        val paddingValue = contentView.resources.getDimensionPixelSize(R.dimen.list_vertical_padding)
        contentView.updatePadding(
            top = paddingValue,
            bottom = paddingValue
        )
        contentView.clipToPadding = false
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
