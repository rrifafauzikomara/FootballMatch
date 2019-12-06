package com.rifafauzi.footballmatch.repository.standings

import com.rifafauzi.footballmatch.api.ApiService
import com.rifafauzi.footballmatch.model.standing.StandingsResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-12-06.
 */
 
class StandingsRepository @Inject constructor(private val apiService: ApiService) {

    fun getAllStandings(idLeagues: String?) : Observable<StandingsResponse> {
        return apiService.getAllStandings(idLeagues)
    }

}