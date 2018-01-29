package com.github.syafdia.androidboilerplate.feature.splash

import com.github.syafdia.androidboilerplate.config.AppConfig
import com.github.syafdia.androidboilerplate.feature.BaseViewModel
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class SplashViewModel(
        private val checkUserAuthenticationUseCase: CheckUserAuthenticationUseCase
): BaseViewModel() {

    lateinit var navigator: SplashNavigator

    fun onGrantedPermissions() {
        Observable
                .timer(AppConfig.SPLASH_TIME_OUT, TimeUnit.SECONDS)
                .subscribe {
                    val isUserAuthenticated = checkUserAuthenticationUseCase.execute()

                    if (isUserAuthenticated) {
                        navigator.openDashboardActivity()
                    } else {
                        navigator.openLoginActivity()
                    }
                }
    }
}