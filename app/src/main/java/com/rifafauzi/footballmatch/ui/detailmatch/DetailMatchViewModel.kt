package com.rifafauzi.footballmatch.ui.detailmatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseResponse
import com.rifafauzi.footballmatch.base.BaseViewModel
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.model.match.Match
import com.rifafauzi.footballmatch.model.match.MatchResponse
import com.rifafauzi.footballmatch.model.teams.Team
import com.rifafauzi.footballmatch.model.teams.TeamResponse
import com.rifafauzi.footballmatch.repository.match.MatchRepository
import com.rifafauzi.footballmatch.repository.teams.TeamsRepository
import com.rifafauzi.footballmatch.utils.EspressoIdlingResource
import com.rifafauzi.footballmatch.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-11-24.
 */
 
class DetailMatchViewModel @Inject constructor(private val repository: MatchRepository, private val repositoryTeam: TeamsRepository) : BaseViewModel() {

    private val _detailMatch = MutableLiveData<Result<List<Match>>>()
    val detailMatch: LiveData<Result<List<Match>>>
        get() = _detailMatch

    private val _teamHome = MutableLiveData<Result<List<Team>>>()
    val teamHome: LiveData<Result<List<Team>>>
        get() = _teamHome

    private val _awayTeam = MutableLiveData<Result<List<Team>>>()
    val awayTeam: LiveData<Result<List<Team>>>
        get() = _awayTeam

    fun getDetailMatch(idEvent: String) {
        EspressoIdlingResource.increment()
        mCompositeDisposable += repository.getDetailMatch(idEvent)
            .map { transformData(it) }
            .doOnSubscribe { setResultMatch(Result.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseResponse<List<Match>>() {
                override fun onSuccess(response: List<Match>) {
                    if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
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

    private fun transformData(data: MatchResponse) : List<Match> {
        val match = mutableListOf<Match>()

        for (i in data.events) {
            match.add(
                Match(
                    i.idEvent,
                    i.strHomeTeam,
                    i.strAwayTeam,
                    i.dateEvent,
                    i.intHomeScore,
                    i.intAwayScore,
                    i.strLeague,
                    i.strHomeGoalDetails,
                    i.strAwayGoalDetails,
                    i.strHomeLineupSubstitutes,
                    i.strAwayLineupSubstitutes,
                    i.strHomeLineupGoalkeeper,
                    i.strAwayLineupGoalkeeper,
                    i.strHomeLineupDefense,
                    i.strAwayLineupDefense,
                    i.strHomeLineupMidfield,
                    i.strAwayLineupMidfield,
                    i.strHomeLineupForward,
                    i.strAwayLineupForward,
                    i.idHomeTeam,
                    i.idAwayTeam,
                    i.strSport
                )
            )
        }
        return match.toList()
    }

    private fun setResultMatch(result: Result<List<Match>>) {
        _detailMatch.postValue(result)
    }

    fun getDetailTeamHome(idTeam: String?) {
        EspressoIdlingResource.increment()
        mCompositeDisposable += repositoryTeam.getTeam(idTeam)
            .map { transformDataHomeTeam(it) }
            .doOnSubscribe { setResultHomeTeam(Result.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseResponse<List<Team>>() {
                override fun onSuccess(response: List<Team>) {
                    if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                    if (response.isEmpty()) {
                        setResultHomeTeam(Result.NoData())
                        return
                    }
                    setResultHomeTeam(Result.HasData(response))
                }

                override fun onNoInternetConnection() {
                    setResultHomeTeam(Result.NoInternetConnection())
                }

                override fun onTimeout() {
                    setResultHomeTeam(Result.Error(R.string.timeout))
                }

                override fun onUnknownError(message: String) {
                    setResultHomeTeam(Result.Error(R.string.unknown_error))
                }

            })
    }

    private fun transformDataHomeTeam(data: TeamResponse) : List<Team> {
        val team = mutableListOf<Team>()

        for (i in data.teams) {
            team.add(
                Team(
                    i.idTeam,
                    i.strTeamBadge
                )
            )
        }
        return team.toList()
    }

    fun getDetailTeamAway(idTeam: String?) {
        EspressoIdlingResource.increment()
        mCompositeDisposable += repositoryTeam.getTeam(idTeam)
            .map { transformDataAwayTeam(it) }
            .doOnSubscribe { setResultAwayTeam(Result.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseResponse<List<Team>>() {
                override fun onSuccess(response: List<Team>) {
                    if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                    if (response.isEmpty()) {
                        setResultAwayTeam(Result.NoData())
                        return
                    }
                    setResultAwayTeam(Result.HasData(response))
                }

                override fun onNoInternetConnection() {
                    setResultAwayTeam(Result.NoInternetConnection())
                }

                override fun onTimeout() {
                    setResultAwayTeam(Result.Error(R.string.timeout))
                }

                override fun onUnknownError(message: String) {
                    setResultAwayTeam(Result.Error(R.string.unknown_error))
                }

            })
    }

    private fun setResultHomeTeam(result: Result<List<Team>>) {
        _teamHome.postValue(result)
    }

    private fun transformDataAwayTeam(data: TeamResponse) : List<Team> {
        val team = mutableListOf<Team>()

        for (i in data.teams) {
            team.add(
                Team(
                    i.idTeam,
                    i.strTeamBadge
                )
            )
        }
        return team.toList()
    }

    private fun setResultAwayTeam(result: Result<List<Team>>) {
        _awayTeam.postValue(result)
    }

}