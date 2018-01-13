package com.github.syafdia.androidboilerplate.feature.splash

import com.github.syafdia.androidboilerplate.core.Auth
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import com.github.syafdia.androidboilerplate.data.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @Provides
    fun provideSplashViewModel(auth: Auth): SplashViewModel {
        return SplashViewModel(auth)
    }

}