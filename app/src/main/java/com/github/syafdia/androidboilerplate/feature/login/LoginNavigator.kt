package com.github.syafdia.androidboilerplate.feature.login


interface LoginNavigator {

    fun handleError(throwable: Throwable)

    fun openDashboardActivity()
}