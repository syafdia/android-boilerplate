package com.github.syafdia.androidboilerplate.feature.splash

import android.arch.lifecycle.ViewModel
import com.github.syafdia.androidboilerplate.data.repository.UserRepository


class SplashViewModel(var userRepository: UserRepository): ViewModel() {

    fun startFoo() {
    }
}