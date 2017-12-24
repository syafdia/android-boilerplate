package com.github.syafdia.androidboilerplate.feature.dashboard

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.syafdia.androidboilerplate.core.auth.Auth
import com.github.syafdia.androidboilerplate.core.provider.ResourceProvider
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import com.github.syafdia.androidboilerplate.data.repository.UserRepository


class DashboardViewModelFactory(
        private val auth: Auth,
        private val resourceProvider: ResourceProvider,
        private val schedulerProvider: SchedulerProvider,
        private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(auth, resourceProvider, schedulerProvider, userRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}