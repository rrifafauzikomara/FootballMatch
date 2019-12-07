package com.rifafauzi.footballmatch.ui.standings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseResponse
import com.rifafauzi.footballmatch.base.BaseViewModel
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.model.standing.Standings
import com.rifafauzi.footballmatch.model.standing.StandingsResponse
import com.rifafauzi.footballmatch.model.teams.Team
import com.rifafauzi.footballmatch.repository.standings.StandingsRepository
import com.rifafauzi.footballmatch.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-12-06.
 */
 
class StandingsViewModel @Inject constructor(private val repository: StandingsRepository) : BaseViewModel() {

    private val _team = MutableLiveData<Result<List<Team>>>()
    val team: LiveData<Result<List<Team>>>
        get() = _team

    private val _standing = MutableLiveData<Result<List<Standings>>>()
    val standing: LiveData<Result<List<Standings>>>
        get() = _standing

    fun getListStanding(idLeagues: String?) {
        mCompositeDisposable += repository.getAllStandings(idLeagues)
            .map { transformData(it) }
            .doOnSubscribe { setResultListStanding(Result.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseResponse<List<Standings>>() {
                override fun onSuccess(response: List<Standings>) {
                    if (response.isEmpty()) {
                        setResultListStanding(Result.NoData())
                        return
                    }
                    setResultListStanding(Result.HasData(response))
                }

                override fun onNoInternetConnection() {
                    setResultListStanding(Result.NoInternetConnection())
                }

                override fun onTimeout() {
                    setResultListStanding(Result.Error(R.string.timeout))
                }

                override fun onUnknownError(message: String) {
                    setResultListStanding(Result.Error(R.string.unknown_error))
                }
            })
    }

    private fun transformData(data: StandingsResponse) : List<Standings> {

        val standings = mutableListOf<Standings>()

        // check if data from api is null
        if (data.table.isNullOrEmpty()) {
            setResultListStanding(Result.NoData())
            return mutableListOf()
        }

        for (i in data.table) {
            standings.add(
                Standings(
                    i.name,
                    i.teamId,
                    i.played,
                    i.win,
                    i.draw,
                    i.loss,
                    i.total
                )
            )
        }
        return standings.toList()
    }

    private fun setResultListStanding(result: Result<List<Standings>>) {
        _standing.postValue(result)
    }

}