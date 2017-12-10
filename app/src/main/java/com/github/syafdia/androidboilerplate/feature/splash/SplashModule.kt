package com.github.syafdia.androidboilerplate.feature.splash

import com.github.syafdia.androidboilerplate.data.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @Provides
    fun provideSplashViewModel(userRepository: UserRepository): SplashViewModel {
        return SplashViewModel(userRepository)
    }

}