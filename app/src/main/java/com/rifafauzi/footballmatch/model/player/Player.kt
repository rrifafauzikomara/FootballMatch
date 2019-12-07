package com.rifafauzi.footballmatch.model.player

import com.google.gson.annotations.SerializedName

/**
 * Created by rrifafauzikomara on 2019-12-06.
 */
 
data class Player(
    @SerializedName("idPlayer")
    val idPlayer: String,

    @SerializedName("idTeam")
    val idTeam: String?,

    @SerializedName("strPlayer")
    val strPlayer: String?,

    @SerializedName("strTeam")
    val strTeam: String?,

    @SerializedName("strNationality")
    val strNationality: String?,

    @SerializedName("strDescriptionEN")
    val strDescriptionEN: String?,

    @SerializedName("strPosition")
    val strPosition: String?,

    @SerializedName("strCutout")
    val strCutout: String?,

    @SerializedName("strThumb")
    val strThumb: String?
)