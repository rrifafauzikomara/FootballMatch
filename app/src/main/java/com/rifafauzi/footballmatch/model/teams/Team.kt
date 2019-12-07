package com.rifafauzi.footballmatch.model.teams

import com.google.gson.annotations.SerializedName

/**
 * Created by rrifafauzikomara on 2019-11-25.
 */
 
data class Team(

    @SerializedName("idTeam")
    val idTeam: String,

    @SerializedName("strTeamBadge")
    val strTeamBadge: String?,

    @SerializedName("strTeam")
    val strTeam: String?,

    @SerializedName("strDescriptionEN")
    val strDescriptionEN: String?,

    @SerializedName("strLeague")
    val strLeague: String?,

    @SerializedName("strStadium")
    val strStadium: String?,

    @SerializedName("strStadiumLocation")
    val strStadiumLocation: String?,

    @SerializedName("strStadiumDescription")
    val strStadiumDescription: String?,

    @SerializedName("intStadiumCapacity")
    val intStadiumCapacity: String?,

    @SerializedName("strCountry")
    val strCountry: String?,

    @SerializedName("strStadiumThumb")
    val strStadiumThumb: String?,

    @SerializedName("strTeamJersey")
    val strTeamJersey: String?,

    @SerializedName("strTeamBanner")
    val strTeamBanner: String?,

    @SerializedName("strSport")
    var strSport: String?
)