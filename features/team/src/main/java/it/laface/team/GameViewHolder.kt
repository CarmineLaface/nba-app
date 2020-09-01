package it.laface.team

import it.laface.common.util.getCompleteDayName
import it.laface.common.util.toCalendar
import it.laface.common.view.BaseViewHolder
import it.laface.common.view.bindImage
import it.laface.domain.model.imageUrl
import it.laface.schedule.domain.Game
import it.laface.team.databinding.ItemTeamgameBinding

class GameViewHolder(private val binding: ItemTeamgameBinding) :
    BaseViewHolder<Game>(binding.root) {

    override fun bind(item: Game) {
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
