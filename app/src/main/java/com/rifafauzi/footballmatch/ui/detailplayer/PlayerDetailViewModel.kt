package com.rifafauzi.footballmatch.ui.detailplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseResponse
import com.rifafauzi.footballmatch.base.BaseViewModel
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.model.player.Player
import com.rifafauzi.footballmatch.model.player.PlayerResponse
import com.rifafauzi.footballmatch.repository.player.PlayerRepository
import com.rifafauzi.footballmatch.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-12-06.
 */
 
class PlayerDetailViewModel @Inject constructor(private val repository: PlayerRepository) : BaseViewModel() {

    private val _player = MutableLiveData<Result<List<Player>>>()
    val player: LiveData<Result<List<Player>>>
        get() = _player

    fun getDetailPlayer(idPlayer: String?) {
        mCompositeDisposable += repository.getDetailPlayer(idPlayer)
            .map { transformData(it) }
            .doOnSubscribe { setResultPlayer(Result.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseResponse<List<Player>>() {
                override fun onSuccess(response: List<Player>) {
                    if (response.isEmpty()) {
                        setResultPlayer(Result.NoData())
                        return
                    }
                    setResultPlayer(Result.HasData(response))
                }

                override fun onNoInternetConnection() {
                    setResultPlayer(Result.NoInternetConnection())
                }

                override fun onTimeout() {
                    setResultPlayer(Result.Error(R.string.timeout))
                }

                override fun onUnknownError(message: String) {
                    setResultPlayer(Result.Error(R.string.unknown_error))
                }
            })
    }

    private fun transformData(data: PlayerResponse) : List<Player> {

        val players = mutableListOf<Player>()

        for (i in data.detailPlayer) {
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

    private fun setResultPlayer(result: Result<List<Player>>) {
        _player.postValue(result)
    }

}