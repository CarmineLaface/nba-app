package it.laface.team

import android.os.Bundle
import androidx.fragment.app.Fragment
import it.laface.domain.model.NbaTeam
import it.laface.domain.navigation.Page
import it.laface.domain.navigation.TeamPageProvider

object TeamPageProviderImpl : TeamPageProvider {

    override fun getTeamPage(team: NbaTeam): Page {
        val fragmentClass = TeamFragment::class.java
        val arguments = Bundle(1)
        arguments.putParcelable(TeamFragment.ARGUMENT_KEY, team)
        return Page(
            fragmentClass = fragmentClass as Class<Fragment>,
            arguments = arguments,
            tag = fragmentClass.name
        )
    }
}
