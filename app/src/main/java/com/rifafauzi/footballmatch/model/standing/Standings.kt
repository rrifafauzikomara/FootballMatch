package com.rifafauzi.footballmatch.model.standing

import com.google.gson.annotations.SerializedName

/**
 * Created by rrifafauzikomara on 2019-12-06.
 */
 
data class Standings(
    @SerializedName("name")
    val name: String?,

    @SerializedName("teamid")
    val teamId: String?,

    @SerializedName("played")
    val played: Int?,

    @SerializedName("win")
    val win: Int?,

    @SerializedName("draw")
    val draw: Int?,

    @SerializedName("loss")
    val loss: Int?,

    @SerializedName("total")
    val total: Int?
)