package com.rifafauzi.footballmatch.model.teams

import com.google.gson.annotations.SerializedName

/**
 * Created by rrifafauzikomara on 2019-11-25.
 */
 
data class Team(

    @SerializedName("idTeam")
    val idTeam: String?,

    @SerializedName("strTeamBadge")
    val strTeamBadge: String?
)