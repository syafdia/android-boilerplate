package com.github.syafdia.androidboilerplate.feature.splash

import com.github.syafdia.androidboilerplate.core.Auth
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @Provides
    fun provideSplashViewModel(auth: Auth): SplashViewModel {
        return SplashViewModel(CheckUserAuthenticationUseCase(auth))
    }

}