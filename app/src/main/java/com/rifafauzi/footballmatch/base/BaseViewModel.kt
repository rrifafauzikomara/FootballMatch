package com.rifafauzi.footballmatch.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */

abstract class BaseViewModel : ViewModel() {

    private var isLoading = ObservableBoolean()
    var mCompositeDisposable = CompositeDisposable()

    fun getCompositeDisposable() = mCompositeDisposable


    fun setLoading(setAsLoading: Boolean) = isLoading.set(setAsLoading)
    fun isLoading() = isLoading

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }

}