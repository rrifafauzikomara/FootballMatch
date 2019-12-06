package com.rifafauzi.footballmatch.ui.leagues

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
 * Created by rrifafauzikomara on 2019-11-22.
 */
 
class LeaguesViewModel @Inject constructor(private val repository: LeaguesRepository) : BaseViewModel() {

    private val _leagues = MutableLiveData<Result<List<Leagues>>>()
    val leagues: LiveData<Result<List<Leagues>>>
        get() = _leagues

    fun getListLeagues() {
        mCompositeDisposable += repository.getListLeagues()
            .map { transformData(it) }
            .doOnSubscribe { setResultListLeagues(Result.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseResponse<List<Leagues>>() {
                override fun onSuccess(response: List<Leagues>) {
                    if (response.isEmpty()) {
                        setResultListLeagues(Result.NoData())
                        return
                    }
                    setResultListLeagues(Result.HasData(response))
                }

                override fun onNoInternetConnection() {
                    setResultListLeagues(Result.NoInternetConnection())
                }

                override fun onTimeout() {
                    setResultListLeagues(Result.Error(R.string.timeout))
                }

                override fun onUnknownError(message: String) {
                    setResultListLeagues(Result.Error(R.string.unknown_error))
                }
            })
    }

    private fun transformData(data: LeaguesResponse) : List<Leagues> {

        val leagues = mutableListOf<Leagues>()

        for (i in data.country) {
            leagues.add(
                Leagues(
                    i.idLeague,
                    i.strLeague,
                    i.dateFirstEvent,
                    i.strBanner,
                    i.strSport,
                    i.strDescriptionEN,
                    i.strBadge,
                    i.strCountry,
                    i.strTrophy
                )
            )
        }
        return leagues.toList()
    }

    private fun setResultListLeagues(result: Result<List<Leagues>>) {
        _leagues.postValue(result)
    }

}