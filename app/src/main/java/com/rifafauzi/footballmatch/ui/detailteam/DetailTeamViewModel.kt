package com.rifafauzi.footballmatch.ui.detailteam

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
 
class DetailTeamViewModel @Inject constructor(private val repository: TeamsRepository) : BaseViewModel() {

    private val _detailTeam = MutableLiveData<Result<List<Team>>>()
    val detailTeam: LiveData<Result<List<Team>>>
        get() = _detailTeam

    fun getTeamDetail(idTeam: String?) {
        mCompositeDisposable += repository.getTeam(idTeam)
            .map { transformData(it) }
            .doOnSubscribe { setResultDetailTeam(Result.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseResponse<List<Team>>() {
                override fun onSuccess(response: List<Team>) {
                    if (response.isEmpty()) {
                        setResultDetailTeam(Result.NoData())
                        return
                    }
                    setResultDetailTeam(Result.HasData(response))
                }

                override fun onNoInternetConnection() {
                    setResultDetailTeam(Result.NoInternetConnection())
                }

                override fun onTimeout() {
                    setResultDetailTeam(Result.Error(R.string.timeout))
                }

                override fun onUnknownError(message: String) {
                    setResultDetailTeam(Result.Error(R.string.unknown_error))
                }

            })
    }

    private fun transformData(data: TeamResponse) : List<Team> {
        val teams = mutableListOf<Team>()
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
                    i.strTeamBanner
                )
            )
        }
        return teams.toList()
    }

    private fun setResultDetailTeam(result: Result<List<Team>>) {
        _detailTeam.postValue(result)
    }

}