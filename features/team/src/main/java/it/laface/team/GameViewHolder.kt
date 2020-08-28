package it.laface.team

import android.view.View
import it.laface.common.view.bindImage
import it.laface.schedule.domain.Game
import it.laface.team.databinding.ItemTeamgameBinding
import it.laface.team.viewpager.PageViewHolder

class GameViewHolder(private val binding: ItemTeamgameBinding) :
    PageViewHolder<it.laface.schedule.domain.Game> {

    override val view: View
        get() = binding.root

    override fun bind(item: it.laface.schedule.domain.Game) {
        binding.dateTextView.text = item.date.toCalendar.getCompleteDayName

        binding.homeTeamCityTextView.text = item.homeTeam.cityName
        binding.homeTeamNameTextView.text = item.homeTeam.nickname
        binding.homeTeamLogoImageView.bindImage(item.homeTeam.imageUrl, R.drawable.circle_grey)
        binding.homeScoreTextView.text = item.homeScore ?: "-"

        binding.visitorTeamCityTextView.text = item.visitorTeam.cityName
        binding.visitorTeamNameTextView.text = item.visitorTeam.nickname
        binding.visitorTeamLogoImageView.bindImage(
            item.visitorTeam.imageUrl,
            R.drawable.circle_grey
        )
        binding.visitorScoreTextView.text = item.visitorScore ?: "-"
    }
}
