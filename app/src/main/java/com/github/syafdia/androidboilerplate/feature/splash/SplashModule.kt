package com.github.syafdia.androidboilerplate.feature.splash

import com.github.syafdia.androidboilerplate.core.Auth
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import com.github.syafdia.androidboilerplate.feature.splash.domain.usecase.CheckUserAuthenticationUseCase
import com.github.syafdia.androidboilerplate.feature.splash.presentation.SplashViewModel
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @Provides
    fun provideCheckUserAuthenticationUseCase(auth: Auth): CheckUserAuthenticationUseCase {
        return CheckUserAuthenticationUseCase(auth)
    }

    @Provides
    fun provideSplashViewModel(
            checkUserAuthenticationUseCase: CheckUserAuthenticationUseCase,
            schedulerProvider: SchedulerProvider
    ): SplashViewModel {
        return SplashViewModel(checkUserAuthenticationUseCase, schedulerProvider)
    }

}