package com.rifafauzi.footballmatch.repository.teams

import com.rifafauzi.footballmatch.api.ApiService
import com.rifafauzi.footballmatch.model.teams.TeamResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-11-25.
 */
 
class TeamsRepository @Inject constructor(private val apiService: ApiService) {

    fun getTeam(idTeam: String?) : Observable<TeamResponse> {
        return apiService.getDetailTeams(idTeam)
    }

    fun getAllTeams(idLeagues: String?) : Observable<TeamResponse> {
        return apiService.getAllTeams(idLeagues)
    }

    fun searchTeams(query: String?) : Observable<TeamResponse> {
        return apiService.searchTeam(query)
    }

}