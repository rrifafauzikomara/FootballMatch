package com.rifafauzi.footballmatch.api

import com.rifafauzi.footballmatch.model.LeaguesResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */

interface ApiService {

    @GET("search_all_leagues.php?s=Soccer")
    fun getListLeagues() : Observable<LeaguesResponse>

}