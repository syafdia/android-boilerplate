package com.github.syafdia.androidboilerplate.feature.splash.presentation

import com.github.syafdia.androidboilerplate.config.AppConfig
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import com.github.syafdia.androidboilerplate.feature.BaseViewModel
import com.github.syafdia.androidboilerplate.feature.splash.domain.usecase.CheckUserAuthenticationUseCase
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber
import java.util.concurrent.TimeUnit


class SplashViewModel(
        private val checkUserAuthenticationUseCase: CheckUserAuthenticationUseCase,
        private val schedulerProvider: SchedulerProvider
): BaseViewModel() {

    lateinit var navigator: SplashNavigator

    fun onGrantedPermissions() {
        Single.timer(AppConfig.SPLASH_TIME_OUT, TimeUnit.SECONDS)
                .flatMap { checkUserAuthenticationUseCase.execute() }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .subscribe({ isAuthenticated ->
                    if (isAuthenticated) {
                        navigator.openDashboardActivity()
                    } else {
                        navigator.openLoginActivity()
                    }
                }, { err ->
                    Timber.e(err)
                })
                .let { compositeDisposable.add(it) }
    }
}