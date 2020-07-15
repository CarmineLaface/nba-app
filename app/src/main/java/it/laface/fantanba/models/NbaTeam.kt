package it.laface.fantanba.models

import it.laface.fantanba.teamsAbbreviation

/**
 * Created by Carmine Laface on 13/11/2018.
 */

class NbaTeam(
    val primaryColor: String = "#3C3C3C",
    // var tricode: String? = null,
    // var is_nba_team: Boolean? = null,
    val ttsName: String? = null,
    // var isNBAFranchise: Boolean? = null,
    // var fullName: String? = null,
    val teamId: String? = null
) {
    var win: String? = null
    var loss: String? = null

    val teamSlug: String by lazy {
        ttsName!!.split(' ').run {
            return@lazy get(size - 1).toLowerCase()
        }
    }

    val abbreviation: String by lazy {
        teamsAbbreviation[teamSlug]?.toUpperCase() ?: ""
    }

    // var teamPhoto: Drawable? = null
}
