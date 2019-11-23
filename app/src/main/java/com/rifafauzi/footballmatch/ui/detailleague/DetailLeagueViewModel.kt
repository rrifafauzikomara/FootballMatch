package com.rifafauzi.footballmatch.ui.detailleague

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseResponse
import com.rifafauzi.footballmatch.base.BaseViewModel
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.model.leagues.Leagues
import com.rifafauzi.footballmatch.model.leagues.LeaguesResponse
import com.rifafauzi.footballmatch.repository.leagues.LeaguesRepository
import com.rifafauzi.footballmatch.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-11-23.
 */
 
class DetailLeagueViewModel @Inject constructor(private val repository: LeaguesRepository) : BaseViewModel() {

    private val _detailLeague = MutableLiveData<Result<List<Leagues>>>()
    val detailLeague: LiveData<Result<List<Leagues>>>
        get() = _detailLeague

    fun getDetailLeague(idLeague: String) {
        mCompositeDisposable += repository.getDetailLeague(idLeague)
            .map { transformData(it) }
            .doOnSubscribe { setResultDetailLeague(Result.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseResponse<List<Leagues>>() {
                override fun onSuccess(response: List<Leagues>) {
                    if (response.isEmpty()) {
                        setResultDetailLeague(Result.NoData())
                        return
                    }
                    setResultDetailLeague(Result.HasData(response))
                }

                override fun onNoInternetConnection() {
                    setResultDetailLeague(Result.NoInternetConnection())
                }

                override fun onTimeout() {
                    setResultDetailLeague(Result.Error(R.string.timeout))
                }

                override fun onUnknownError(message: String) {
                    setResultDetailLeague(Result.Error(R.string.unknown_error))
                }
            })
    }

    private fun transformData(data: LeaguesResponse) : List<Leagues> {

        val leagues = mutableListOf<Leagues>()

        for (i in data.leagues) {
            leagues.add(
                Leagues(
                    i.idLeague,
                    i.strLeague,
                    i.dateFirstEvent,
                    i.strBanner,
                    i.strSport,
                    i.strDescriptionEN,
                    i.strBadge,
                    i.strCountry
                )
            )
        }
        return leagues.toList()
    }

    private fun setResultDetailLeague(result: Result<List<Leagues>>) {
        _detailLeague.postValue(result)
    }

}