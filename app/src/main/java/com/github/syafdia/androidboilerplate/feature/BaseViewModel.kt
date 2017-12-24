package com.github.syafdia.androidboilerplate.feature

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()


    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }


}