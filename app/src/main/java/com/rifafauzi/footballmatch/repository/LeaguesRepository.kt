package com.rifafauzi.footballmatch.repository

import com.rifafauzi.footballmatch.api.ApiService
import com.rifafauzi.footballmatch.model.LeaguesResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */
 
class LeaguesRepository @Inject constructor(private val apiService: ApiService) {

    fun getListLeagues() : Observable<LeaguesResponse> {
        return apiService.getListLeagues("Soccer")
    }

}