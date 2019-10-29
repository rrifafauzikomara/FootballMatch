package com.rifafauzi.footballmatch.data.remote

import com.rifafauzi.footballmatch.data.model.LeagueResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by rrifafauzikomara on 2019-10-29.
 */
 
interface ApiService {

    // list league soccer
    @GET("search_all_leagues.php?s=Soccer")
    fun getListLeagues() : Response<LeagueResponse>

//    // detail league
//    @GET("lookupleague.php")
//    fun getDetailLeague(@Query("id") id: String?): Observable<LeagueResponse>
//
//    // detail match
//    @GET("lookupevent.php")
//    fun getDetailEvenet(@Query("id") id: String): Observable<EventResponse>
//
//    // next match
//    @GET("eventsnextleague.php")
//    fun eventNextLeague(@Query("id") id: String): Observable<EventResponse>
//
//    // past match
//    @GET("eventspastleague.php")
//    fun eventPastLeague(@Query("id") id: String): Observable<EventResponse>
//
//    // search match
//    @GET("searchevents.php")
//    fun searchEvents(@Query("e") query: String): Observable<EventResponse>

}