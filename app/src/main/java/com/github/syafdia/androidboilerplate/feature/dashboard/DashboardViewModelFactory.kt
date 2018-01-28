package com.github.syafdia.androidboilerplate.feature.dashboard

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider


class DashboardViewModelFactory(
        private val getAuthUserUseCase: GetAuthUserUseCase,
        private val deleteAuthUserUseCase: DeleteAuthUserUseCase,
        private val getAuthSubjectUseCase: GetAuthSubjectUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(
                    getAuthUserUseCase,
                    deleteAuthUserUseCase,
                    getAuthSubjectUseCase
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}