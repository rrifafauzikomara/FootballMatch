package com.rifafauzi.footballmatch.ui.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseResponse
import com.rifafauzi.footballmatch.base.BaseViewModel
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.model.player.Player
import com.rifafauzi.footballmatch.model.player.PlayerResponse
import com.rifafauzi.footballmatch.model.teams.Team
import com.rifafauzi.footballmatch.model.teams.TeamResponse
import com.rifafauzi.footballmatch.repository.player.PlayerRepository
import com.rifafauzi.footballmatch.repository.teams.TeamsRepository
import com.rifafauzi.footballmatch.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-12-06.
 */
 
class PlayerListViewModel @Inject constructor(private val repository: PlayerRepository, private val teamsRepository: TeamsRepository) : BaseViewModel() {

    private val _player = MutableLiveData<Result<List<Player>>>()
    val player: LiveData<Result<List<Player>>>
        get() = _player

    private val _detailTeam = MutableLiveData<Result<List<Team>>>()
    val detailTeam: LiveData<Result<List<Team>>>
        get() = _detailTeam

    fun getTeamDetail(idTeam: String?) {
        mCompositeDisposable += teamsRepository.getTeam(idTeam)
            .map { transformTeam(it) }
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

    private fun transformTeam(data: TeamResponse) : List<Team> {
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
                    i.strTeamBanner,
                    i.strSport
                )
            )
        }
        return teams.toList()
    }

    private fun setResultDetailTeam(result: Result<List<Team>>) {
        _detailTeam.postValue(result)
    }

    fun getListPlayer(teamName: String?) {
        mCompositeDisposable += repository.getAllPlayer(teamName)
            .map { transformData(it) }
            .doOnSubscribe { setResultListPlayer(Result.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseResponse<List<Player>>() {
                override fun onSuccess(response: List<Player>) {
                    if (response.isEmpty()) {
                        setResultListPlayer(Result.NoData())
                        return
                    }
                    setResultListPlayer(Result.HasData(response))
                }

                override fun onNoInternetConnection() {
                    setResultListPlayer(Result.NoInternetConnection())
                }

                override fun onTimeout() {
                    setResultListPlayer(Result.Error(R.string.timeout))
                }

                override fun onUnknownError(message: String) {
                    setResultListPlayer(Result.Error(R.string.unknown_error))
                }
            })
    }

    private fun transformData(data: PlayerResponse) : List<Player> {

        val players = mutableListOf<Player>()

        // check if data from api is null
        if (data.allPlayer.isNullOrEmpty()) {
            setResultListPlayer(Result.NoData())
            return mutableListOf()
        }

        for (i in data.allPlayer) {
            players.add(
                Player(
                    i.idPlayer,
                    i.idTeam,
                    i.strPlayer,
                    i.strTeam,
                    i.strNationality,
                    i.strDescriptionEN,
                    i.strPosition,
                    i.strCutout,
                    i.strThumb
                )
            )
        }
        return players.toList()
    }

    private fun setResultListPlayer(result: Result<List<Player>>) {
        _player.postValue(result)
    }

}