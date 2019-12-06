package com.rifafauzi.footballmatch.repository.player

import com.rifafauzi.footballmatch.api.ApiService
import com.rifafauzi.footballmatch.model.player.PlayerResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-12-06.
 */
 
class PlayerRepository @Inject constructor(private val apiService: ApiService) {

    fun getAllPlayer(teamName: String?) : Observable<PlayerResponse> {
        return apiService.getAllPlayer(teamName)
    }

    fun getDetailPlayer(idPlayer: String?) : Observable<PlayerResponse> {
        return apiService.getDetailPlayer(idPlayer)
    }

}