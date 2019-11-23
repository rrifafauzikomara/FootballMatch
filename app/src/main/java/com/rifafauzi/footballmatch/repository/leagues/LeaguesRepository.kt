package com.rifafauzi.footballmatch.repository.leagues

import com.rifafauzi.footballmatch.api.ApiService
import com.rifafauzi.footballmatch.model.leagues.LeaguesResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */
 
class LeaguesRepository @Inject constructor(private val apiService: ApiService) {

    fun getListLeagues() : Observable<LeaguesResponse> {
        return apiService.getListLeagues("Soccer")
    }

    fun getDetailLeague(idLeague: String) : Observable<LeaguesResponse> {
        return apiService.getDetailLeague(idLeague)
    }

}