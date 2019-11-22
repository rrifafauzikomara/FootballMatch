package com.rifafauzi.footballmatch.model

import com.google.gson.annotations.SerializedName

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */
 
data class Leagues(

    @SerializedName("idLeague")
    val idLeague: String,

    @SerializedName("strLeague")
    val strLeague: String,

    @SerializedName("dateFirstEvent")
    val dateFirstEvent: String,

    @SerializedName("strBanner")
    val strBanner: String,

    @SerializedName("strSport")
    val strSport: String,

    @SerializedName("strDescriptionEN")
    val strDescriptionEN: String,

    @SerializedName("strBadge")
    val strBadge: String,

    @SerializedName("strCountry")
    val strCountry: String
)