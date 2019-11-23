package com.rifafauzi.footballmatch.model.match

import com.google.gson.annotations.SerializedName

/**
 * Created by rrifafauzikomara on 2019-11-23.
 */
 
data class Match(

    @SerializedName("idEvent")
    val idEvent: String,

    @SerializedName("strHomeTeam")
    val strHomeTeam: String?,

    @SerializedName("strAwayTeam")
    val strAwayTeam: String?,

    @SerializedName("dateEvent")
    val dateEvent: String?,

    @SerializedName("intHomeScore")
    val intHomeScore: String?,

    @SerializedName("intAwayScore")
    val intAwayScore: String?
)