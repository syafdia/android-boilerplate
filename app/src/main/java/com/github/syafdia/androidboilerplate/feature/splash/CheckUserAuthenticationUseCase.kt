package com.github.syafdia.androidboilerplate.feature.splash

import com.github.syafdia.androidboilerplate.core.Auth


class CheckUserAuthenticationUseCase(private val auth: Auth) {

    fun execute(): Boolean {
        return auth.isAuthenticated()
    }
}