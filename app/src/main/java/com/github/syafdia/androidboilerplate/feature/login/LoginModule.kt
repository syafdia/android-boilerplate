package com.github.syafdia.androidboilerplate.feature.login

import com.github.syafdia.androidboilerplate.core.auth.Auth
import com.github.syafdia.androidboilerplate.core.provider.ResourceProvider
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import com.github.syafdia.androidboilerplate.data.repository.UserRepository
import dagger.Module
import dagger.Provides


@Module
class LoginModule {

    @Provides
    fun provideLoginViewModelFactory(
            auth: Auth,
            resourceProvider: ResourceProvider,
            schedulerProvider: SchedulerProvider,
            userRepository: UserRepository
    ): LoginViewModelFactory {

        return LoginViewModelFactory(auth, resourceProvider, schedulerProvider, userRepository)
    }
}