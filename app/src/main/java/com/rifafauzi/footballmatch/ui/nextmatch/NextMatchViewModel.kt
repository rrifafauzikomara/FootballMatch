package com.rifafauzi.footballmatch.ui.nextmatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseResponse
import com.rifafauzi.footballmatch.base.BaseViewModel
import com.rifafauzi.footballmatch.model.match.Match
import com.rifafauzi.footballmatch.model.match.MatchResponse
import com.rifafauzi.footballmatch.repository.match.MatchRepository
import com.rifafauzi.footballmatch.utils.plusAssign
import com.rifafauzi.footballmatch.common.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-11-23.
 */
 
class NextMatchViewModel @Inject constructor(private val repository: MatchRepository) : BaseViewModel() {

    private val _nextMatch = MutableLiveData<Result<List<Match>>>()
    val nextMatch: LiveData<Result<List<Match>>>
        get() = _nextMatch

    fun getNextMatch(idLeague: String) {
        mCompositeDisposable += repository.getNextMatch(idLeague)
            .map { transformData(it) }
            .doOnSubscribe { setResultMatch(Result.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseResponse<List<Match>>() {
                override fun onSuccess(response: List<Match>) {
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

        // check if data from api is null
        if (data.events.isNullOrEmpty()) {
            setResultMatch(Result.NoData())
            return mutableListOf()
        }

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
        _nextMatch.postValue(result)
    }

}