package com.rifafauzi.footballmatch.ui.searchteam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseResponse
import com.rifafauzi.footballmatch.base.BaseViewModel
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.model.teams.Team
import com.rifafauzi.footballmatch.model.teams.TeamResponse
import com.rifafauzi.footballmatch.repository.teams.TeamsRepository
import com.rifafauzi.footballmatch.utils.TYPE_SPORT
import com.rifafauzi.footballmatch.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-12-06.
 */
 
class SearchTeamViewModel @Inject constructor(private val repository: TeamsRepository) : BaseViewModel() {

    private val _searchTeam = MutableLiveData<Result<List<Team>>>()
    val searchTeam: LiveData<Result<List<Team>>>
        get() = _searchTeam

    fun searchMatch(query: String) {
        mCompositeDisposable += repository.searchTeams(query)
            .map { transformData(it) }
            .doOnSubscribe { setResultMatch(Result.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseResponse<List<Team>>() {
                override fun onSuccess(response: List<Team>) {
                    if (response.isEmpty()) {
                        setResultMatch(Result.NoData())
                        return
                    }
                    setResultMatch(Result.HasData(response))
                }

                override fun onNoInternetConnection() {
                    setResultMatch(Result.NoInternetConnection())
                }

                override fun onTimeout() {
                    setResultMatch(Result.Error(R.string.timeout))
                }

                override fun onUnknownError(message: String) {
                    setResultMatch(Result.Error(R.string.unknown_error))
                }
            })
    }

    private fun transformData(data: TeamResponse) : List<Team> {
        val team = mutableListOf<Team>()

        // check if data from api is null
        if (data.teams.isNullOrEmpty()) {
            setResultMatch(Result.NoData())
            return mutableListOf()
        }

        for (i in data.teams) {
            if (i.strSport.equals(TYPE_SPORT)) {
                team.add(
                    Team(
                        i.idTeam,
                        i.strTeamBadge,
                        i.strTeam,
                        i.strDescriptionEN,
                        i.strLeague,
                        i.strStadium,
                        i.strStadiumLocation,
                        i.strStadiumDescription,
                        i.intStadiumCapacity,
                        i.strCountry,
                        i.strStadiumThumb,
                        i.strTeamJersey,
                        i.strTeamBanner,
                        i.strSport
                    )
                )
            }
        }
        return team.toList()
    }

    private fun setResultMatch(result: Result<List<Team>>) {
        _searchTeam.postValue(result)
    }

}