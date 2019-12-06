package com.rifafauzi.footballmatch.model.standing

import com.google.gson.annotations.SerializedName

/**
 * Created by rrifafauzikomara on 2019-12-06.
 */
 
data class StandingsResponse(
    @SerializedName("table")
    val table: List<Standings>
)