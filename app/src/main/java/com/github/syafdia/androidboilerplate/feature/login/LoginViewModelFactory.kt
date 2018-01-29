package com.github.syafdia.androidboilerplate.feature.login

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.syafdia.androidboilerplate.core.Auth
import com.github.syafdia.androidboilerplate.core.provider.ResourceProvider
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import com.github.syafdia.androidboilerplate.data.repository.UserRepository


class LoginViewModelFactory(
        private val authenticateUserUseCase: AuthenticateUserUseCase,
        private val saveAuthUserUseCase: SaveAuthUserUseCase,
        private val resourceProvider: ResourceProvider,
        private val schedulerProvider: SchedulerProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                    authenticateUserUseCase,
                    saveAuthUserUseCase,
                    resourceProvider,
                    schedulerProvider
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}