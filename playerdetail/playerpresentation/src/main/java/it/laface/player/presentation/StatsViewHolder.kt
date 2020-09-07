package it.laface.player.presentation

import it.laface.common.view.BaseViewHolder
import it.laface.player.presentation.databinding.ItemStatisticBinding

class StatsViewHolder(
    private val binding: ItemStatisticBinding,
) : BaseViewHolder<UIPlayerStats>(binding.root) {

    override fun bind(item: UIPlayerStats) {
        val resources = binding.root.context.resources
        binding.nameTextView.text = resources.getString(item.nameStringResourceId)
        binding.valueTextView.text = item.value
    }
}
