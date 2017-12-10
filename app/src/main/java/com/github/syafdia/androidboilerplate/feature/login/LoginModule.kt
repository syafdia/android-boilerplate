package com.github.syafdia.androidboilerplate.feature.login

import com.github.syafdia.androidboilerplate.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector



@Module
class LoginModule {

    @Provides
    fun provideLoginViewModel(userRepository: UserRepository): LoginViewModel {
        return LoginViewModel(userRepository)
    }
}