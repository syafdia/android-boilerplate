package com.github.syafdia.androidboilerplate.feature.splash

import com.github.syafdia.androidboilerplate.config.AppConfig
import com.github.syafdia.androidboilerplate.core.Auth
import com.github.syafdia.androidboilerplate.feature.BaseViewModel
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class SplashViewModel(val auth: Auth): BaseViewModel() {

    lateinit var navigator: SplashNavigator

    fun onGrantedPermissions() {
        Observable
                .timer(AppConfig.SPLASH_TIME_OUT, TimeUnit.SECONDS)
                .subscribe {
                    if (auth.isAuthenticated()) {
                        navigator.openDashboardActivity()
                    } else {
                        navigator.openLoginActivity()
                    }
                }
    }
}