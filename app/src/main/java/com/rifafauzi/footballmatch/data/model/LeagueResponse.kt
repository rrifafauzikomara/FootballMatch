package com.rifafauzi.footballmatch.data.model

import com.google.gson.annotations.SerializedName
import com.rifafauzi.footballmatch.data.local.entity.Leagues

/**
 * Created by rrifafauzikomara on 2019-10-18.
 */

data class LeagueResponse(
	@SerializedName("countrys")
	val leagues: List<Leagues>
)
