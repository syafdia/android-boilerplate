package com.github.syafdia.androidboilerplate.feature.information

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider


class InformationViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InformationViewModel::class.java)) {
            return InformationViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}