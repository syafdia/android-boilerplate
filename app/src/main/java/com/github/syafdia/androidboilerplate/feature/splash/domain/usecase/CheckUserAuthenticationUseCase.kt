package com.github.syafdia.androidboilerplate.feature.splash.domain.usecase

import com.github.syafdia.androidboilerplate.core.Auth
import io.reactivex.Single


class CheckUserAuthenticationUseCase(private val auth: Auth) {

    fun execute(): Single<Boolean> {
        return auth.getData().map { it.isNotEmpty() }
    }
}