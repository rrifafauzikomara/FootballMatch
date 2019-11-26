package com.rifafauzi.footballmatch.repository.match

import com.rifafauzi.footballmatch.api.ApiService
import com.rifafauzi.footballmatch.model.match.MatchResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-11-23.
 */
 
class MatchRepository @Inject constructor(private val apiService: ApiService) {

    fun getNextMatch(idLeague: String?) : Observable<MatchResponse> {
        return apiService.getNextMatch(idLeague)
    }

    fun getPrevMatch(idLeague: String?) : Observable<MatchResponse> {
        return apiService.getPrevMatch(idLeague)
    }

    fun getDetailMatch(idEvent: String?) : Observable<MatchResponse> {
        return apiService.getDetailMatch(idEvent)
    }

    fun searchMatch(query: String) : Observable<MatchResponse> {
        return apiService.searchMatch(query)
    }

}