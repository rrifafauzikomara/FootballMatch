package com.rifafauzi.footballmatch.data.repository

import androidx.lifecycle.LiveData
import com.rifafauzi.footballmatch.base.BaseRepository
import com.rifafauzi.footballmatch.data.NetworkBoundResources
import com.rifafauzi.footballmatch.data.local.dao.LeaguesDao
import com.rifafauzi.footballmatch.data.local.entity.Leagues
import com.rifafauzi.footballmatch.data.model.LeagueResponse
import com.rifafauzi.footballmatch.data.remote.ApiService
import com.rifafauzi.footballmatch.vo.Result
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-10-29.
 */
 
class Repository @Inject constructor(
    private val leaguesDao: LeaguesDao,
    private val apiService: ApiService
) : BaseRepository() {

    fun getAllLeagues(): LiveData<Result<List<Leagues>>> {
        return object : NetworkBoundResources<List<Leagues>, LeagueResponse>() {
            override fun loadFromDb(): LiveData<List<Leagues>> {
                return leaguesDao.getAllLeagues()
            }

            override fun shouldFetch(data: List<Leagues>?): Boolean = true

            override suspend fun createCall(): Result<LeagueResponse> {
                return getApiResult { apiService.getListLeagues() }
            }

            override suspend fun saveCallResult(response: LeagueResponse) {
                leaguesDao.insertAll(response.leagues)
            }

        }.asLiveData()
    }

}