package com.github.syafdia.androidboilerplate.feature.dashboard

import com.github.syafdia.androidboilerplate.core.Auth
import com.github.syafdia.androidboilerplate.data.model.User

class GetAuthUserUseCase(private val auth: Auth) {

    fun execute(): User? {
        return auth.getUser()
    }
}