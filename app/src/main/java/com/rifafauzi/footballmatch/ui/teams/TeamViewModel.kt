package com.rifafauzi.footballmatch.ui.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseResponse
import com.rifafauzi.footballmatch.base.BaseViewModel
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.model.teams.Team
import com.rifafauzi.footballmatch.model.teams.TeamResponse
import com.rifafauzi.footballmatch.repository.teams.TeamsRepository
import com.rifafauzi.footballmatch.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-12-06.
 */
 
class TeamViewModel @Inject constructor(private val repository: TeamsRepository) : BaseViewModel() {

    private val _teams = MutableLiveData<Result<List<Team>>>()
    val team: LiveData<Result<List<Team>>>
        get() = _teams

    fun getListTeams(idLeagues: String?) {
        mCompositeDisposable += repository.getAllTeams(idLeagues)
            .map { transformData(it) }
            .doOnSubscribe { setResultListTeams(Result.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseResponse<List<Team>>() {
                override fun onSuccess(response: List<Team>) {
                    if (response.isEmpty()) {
                        setResultListTeams(Result.NoData())
                        return
                    }
                    setResultListTeams(Result.HasData(response))
                }

                override fun onNoInternetConnection() {
                    setResultListTeams(Result.NoInternetConnection())
                }

                override fun onTimeout() {
                    setResultListTeams(Result.Error(R.string.timeout))
                }

                override fun onUnknownError(message: String) {
                    setResultListTeams(Result.Error(R.string.unknown_error))
                }

            })
    }

    private fun transformData(data: TeamResponse) : List<Team> {
        val teams = mutableListOf<Team>()

        // check if data from api is null
        if (data.teams.isNullOrEmpty()) {
            setResultListTeams(Result.NoData())
            return mutableListOf()
        }

        for (i in data.teams) {
            teams.add(
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
        return teams.toList()
    }

    private fun setResultListTeams(result: Result<List<Team>>) {
        _teams.postValue(result)
    }

}