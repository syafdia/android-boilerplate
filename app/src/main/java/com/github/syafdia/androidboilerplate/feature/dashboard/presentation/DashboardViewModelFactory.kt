package com.github.syafdia.androidboilerplate.feature.dashboard.presentation

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import com.github.syafdia.androidboilerplate.feature.dashboard.domain.usecase.DeleteAuthUserUseCase
import com.github.syafdia.androidboilerplate.feature.dashboard.domain.usecase.GetAuthSubjectUseCase
import com.github.syafdia.androidboilerplate.feature.dashboard.domain.usecase.GetAuthUserUseCase


class DashboardViewModelFactory(
        private val getAuthUserUseCase: GetAuthUserUseCase,
        private val deleteAuthUserUseCase: DeleteAuthUserUseCase,
        private val getAuthSubjectUseCase: GetAuthSubjectUseCase,
        private val schedulerProvider: SchedulerProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(
                    getAuthUserUseCase,
                    deleteAuthUserUseCase,
                    getAuthSubjectUseCase,
                    schedulerProvider
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}