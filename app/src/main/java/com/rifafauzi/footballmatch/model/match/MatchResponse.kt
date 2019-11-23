package com.rifafauzi.footballmatch.model.match

import com.google.gson.annotations.SerializedName

/**
 * Created by rrifafauzikomara on 2019-11-23.
 */
 
data class MatchResponse(
    @SerializedName("events")
    val events: List<Match>
)