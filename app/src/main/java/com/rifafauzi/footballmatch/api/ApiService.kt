package com.rifafauzi.footballmatch.api

import com.rifafauzi.footballmatch.model.leagues.LeaguesResponse
import com.rifafauzi.footballmatch.model.match.MatchResponse
import com.rifafauzi.footballmatch.model.player.PlayerResponse
import com.rifafauzi.footballmatch.model.standing.StandingsResponse
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
    fun getDetailTeams(@Query("id") idTeam: String?) : Observable<TeamResponse>

    @GET("lookup_all_teams.php")
    fun getAllTeams(@Query("id") idLeagues: String?) : Observable<TeamResponse>

    @GET("lookuptable.php")
    fun getAllStandings(@Query("l") idLeagues: String?) : Observable<StandingsResponse>

    @GET("searchplayers.php")
    fun getAllPlayer(@Query("t") teamName: String?) : Observable<PlayerResponse>

    @GET("lookupplayer.php")
    fun getDetailPlayer(@Query("id") idPlayer: String?) : Observable<PlayerResponse>

    @GET("searchteams.php")
    fun searchTeam(@Query("t") query: String?) : Observable<TeamResponse>

}