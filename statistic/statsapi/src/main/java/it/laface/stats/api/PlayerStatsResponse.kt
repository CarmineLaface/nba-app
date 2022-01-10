package it.laface.stats.api

import it.laface.stats.domain.StatsSection

data class PlayerStatsResponse(
    val sections: List<StatsSection>
)
