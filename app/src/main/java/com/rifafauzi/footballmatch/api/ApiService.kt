package com.rifafauzi.footballmatch.api

import com.rifafauzi.footballmatch.model.LeaguesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */

interface ApiService {

    @GET("search_all_leagues.php")
    fun getListLeagues(@Query("s") sport: String) : Observable<LeaguesResponse>

}