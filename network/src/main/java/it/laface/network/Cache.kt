package it.laface.network

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import it.laface.domain.model.NbaTeam
import it.laface.network.Const.jsonTeamList
import java.lang.reflect.Type

object Cache {

    val teamList2: List<NbaTeam> by lazy {
        val listType: Type = object : TypeToken<List<NbaTeam>>() {}.type
        Gson().fromJson<List<NbaTeam>>(jsonTeamList, listType)
    }
}
