package com.github.syafdia.androidboilerplate.feature.setting

import com.github.syafdia.androidboilerplate.core.auth.Auth
import com.github.syafdia.androidboilerplate.core.provider.ResourceProvider
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import com.github.syafdia.androidboilerplate.feature.BaseViewModel


class SettingViewModel(
        private val auth: Auth,
        private val resourceProvider: ResourceProvider,
        private val schedulerProvider: SchedulerProvider
): BaseViewModel() {

    lateinit var navigator: SettingNavigator

    init {
//        Observable
//                .timer(AppConfig.SPLASH_TIME_OUT, TimeUnit.SECONDS)
//                .subscribe {
//                    auth.logOut()
//                }
    }
}