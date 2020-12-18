package it.laface.stats.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StatsSection(
    val players: List<Leader>,
    val title: String
) : Parcelable

@Parcelize
data class Leader(
    val playerId: String,
    val playerName: String,
    val rank: Int,
    val teamId: String,
    val customValue: String
) : Parcelable
