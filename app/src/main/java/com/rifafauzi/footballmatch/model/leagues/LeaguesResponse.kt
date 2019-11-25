package com.rifafauzi.footballmatch.model.leagues

import com.google.gson.annotations.SerializedName

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */

data class LeaguesResponse(

    @SerializedName("countrys")
    val country: List<Leagues>,

    @SerializedName("leagues")
    val leagues: List<Leagues>

)