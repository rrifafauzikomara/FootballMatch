package com.rifafauzi.footballmatch.ui.detailmatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseResponse
import com.rifafauzi.footballmatch.base.BaseViewModel
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.model.match.Match
import com.rifafauzi.footballmatch.model.match.MatchResponse
import com.rifafauzi.footballmatch.repository.match.MatchRepository
import com.rifafauzi.footballmatch.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-11-24.
 */
 
class DetailMatchViewModel @Inject constructor(private val repository: MatchRepository) : BaseViewModel() {

    private val _detailMatch = MutableLiveData<Result<List<Match>>>()
    val detailMatch: LiveData<Result<List<Match>>>
        get() = _detailMatch

    fun getDetailMatch(idEvent: String) {
        mCompositeDisposable += repository.getDetailMatch(idEvent)
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
                    i.idAwayTeam
                )
            )
        }
        return match.toList()
    }

    private fun setResultMatch(result: Result<List<Match>>) {
        _detailMatch.postValue(result)
    }
}