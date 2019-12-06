package com.rifafauzi.footballmatch.model.player

import com.google.gson.annotations.SerializedName

/**
 * Created by rrifafauzikomara on 2019-12-06.
 */
 
data class PlayerResponse(
    @SerializedName("player")
    val allPlayer: List<Player>,

    @SerializedName("players")
    val detailPlayer: List<Player>
)