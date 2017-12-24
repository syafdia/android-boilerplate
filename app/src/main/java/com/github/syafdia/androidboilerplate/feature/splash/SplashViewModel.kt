package com.github.syafdia.androidboilerplate.feature.splash

import com.github.syafdia.androidboilerplate.config.AppConfig
import com.github.syafdia.androidboilerplate.core.auth.Auth
import com.github.syafdia.androidboilerplate.feature.BaseViewModel
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import com.github.syafdia.androidboilerplate.data.repository.UserRepository
import io.reactivex.Maybe
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class SplashViewModel(
        val auth: Auth,
        val schedulerProvider: SchedulerProvider,
        private val userRepository: UserRepository
): BaseViewModel() {

    lateinit var navigator: SplashNavigator

    fun onGrantedPermissions() {
        userRepository.getAuthenticated()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .delay(AppConfig.SPLASH_TIME_OUT, TimeUnit.SECONDS)
                .subscribe({ user -> run {
                    auth.userSubject.onNext(Maybe.just(user))

                    Observable
                            .timer(AppConfig.SPLASH_TIME_OUT, TimeUnit.SECONDS)
                            .subscribe { navigator.openDashboardActivity() }

                }}, { _ -> run {
                    Observable.timer(AppConfig.SPLASH_TIME_OUT, TimeUnit.SECONDS)
                            .subscribe { navigator.openLoginActivity() }
                }})
    }
}