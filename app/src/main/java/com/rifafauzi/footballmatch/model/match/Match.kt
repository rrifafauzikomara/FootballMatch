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
    val intAwayScore: String?,

    @SerializedName("strLeague")
    val strLeague: String?,

    @SerializedName("strHomeGoalDetails")
    val strHomeGoalDetails: String?,

    @SerializedName("strAwayGoalDetails")
    val strAwayGoalDetails: String?,

    @SerializedName("strHomeLineupSubstitutes")
    val strHomeLineupSubstitutes: String?,

    @SerializedName("strAwayLineupSubstitutes")
    val strAwayLineupSubstitutes: String?,

    @SerializedName("strHomeLineupGoalkeeper")
    val strHomeLineupGoalkeeper: String?,

    @SerializedName("strAwayLineupGoalkeeper")
    val strAwayLineupGoalkeeper: String?,

    @SerializedName("strHomeLineupDefense")
    val strHomeLineupDefense: String?,

    @SerializedName("strAwayLineupDefense")
    val strAwayLineupDefense: String?,

    @SerializedName("strHomeLineupMidfield")
    val strHomeLineupMidfield: String?,

    @SerializedName("strAwayLineupMidfield")
    val strAwayLineupMidfield: String?,

    @SerializedName("strHomeLineupForward")
    val strHomeLineupForward: String?,

    @SerializedName("strAwayLineupForward")
    val strAwayLineupForward: String?,

    @SerializedName("idHomeTeam")
    val idHomeTeam: String?,

    @SerializedName("idAwayTeam")
    val idAwayTeam: String?


)