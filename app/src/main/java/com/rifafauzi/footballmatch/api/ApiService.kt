package com.rifafauzi.footballmatch.api

import com.rifafauzi.footballmatch.model.leagues.LeaguesResponse
import com.rifafauzi.footballmatch.model.match.MatchResponse
import com.rifafauzi.footballmatch.model.teams.TeamResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */

interface ApiService {

    @GET("search_all_leagues.php")
    fun getListLeagues(@Query("s") sport: String) : Observable<LeaguesResponse>

    @GET("lookupleague.php")
    fun getDetailLeague(@Query("id") idLeagues: String?) : Observable<LeaguesResponse>

    @GET("eventspastleague.php")
    fun getPrevMatch(@Query("id") idLeagues: String?) : Observable<MatchResponse>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") idLeagues: String?) : Observable<MatchResponse>

    @GET("lookupevent.php")
    fun getDetailMatch(@Query("id") idEvent: String) : Observable<MatchResponse>

    @GET("searchevents.php")
    fun searchMatch(@Query("e") query: String) : Observable<MatchResponse>

    @GET("lookupteam.php")
    fun getTeams(@Query("id") idTeam: String?) : Observable<TeamResponse>

}