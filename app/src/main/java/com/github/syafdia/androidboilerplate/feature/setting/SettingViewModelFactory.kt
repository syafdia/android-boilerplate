package com.github.syafdia.androidboilerplate.feature.setting

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.syafdia.androidboilerplate.core.auth.Auth
import com.github.syafdia.androidboilerplate.core.provider.ResourceProvider
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider


class SettingViewModelFactory(
        private val auth: Auth,
        private val resourceProvider: ResourceProvider,
        private val schedulerProvider: SchedulerProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(auth, resourceProvider, schedulerProvider) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}