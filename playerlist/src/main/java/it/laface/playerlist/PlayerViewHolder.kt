package it.laface.playerlist

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import it.laface.common.view.BaseViewHolder
import it.laface.domain.model.PlayerModel
import it.laface.domain.model.fullName
import it.laface.domain.model.imageUrl
import kotlinx.android.synthetic.main.item_player.view.*

class PlayerViewHolder(
    itemView: View
) : BaseViewHolder<PlayerModel>(itemView, {}) {

    // TODO use view binding
    override fun bind(item: PlayerModel) {
        super.bind(item)

        itemView.constraintLayout.nameTextView.text = item.fullName

        val playerImageView = itemView.constraintLayout.playerImageView
        Glide.with(playerImageView.context)
            .load(item.imageUrl)
            .apply(RequestOptions.placeholderOf(R.drawable.player_placeholder))
            .into(playerImageView)
    }
}
