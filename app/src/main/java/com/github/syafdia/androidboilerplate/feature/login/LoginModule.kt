package com.github.syafdia.androidboilerplate.feature.login

import com.github.syafdia.androidboilerplate.core.Auth
import com.github.syafdia.androidboilerplate.core.provider.ResourceProvider
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import com.github.syafdia.androidboilerplate.data.repository.UserRepository
import com.github.syafdia.androidboilerplate.feature.login.domain.usecase.AuthenticateUserUseCase
import com.github.syafdia.androidboilerplate.feature.login.domain.usecase.SaveAuthUserUseCase
import com.github.syafdia.androidboilerplate.feature.login.presentation.LoginViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class LoginModule {

    @Provides
    fun provideLoginViewModelFactory(
            auth: Auth,
            userRepository: UserRepository,
            resourceProvider: ResourceProvider,
            schedulerProvider: SchedulerProvider
    ): LoginViewModelFactory {

        return LoginViewModelFactory(
                AuthenticateUserUseCase(userRepository),
                SaveAuthUserUseCase(auth),
                resourceProvider,
                schedulerProvider
        )
    }
}