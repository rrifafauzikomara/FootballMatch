package com.rifafauzi.footballmatch.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */

abstract class BaseViewModel : ViewModel() {

    var mCompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }

}