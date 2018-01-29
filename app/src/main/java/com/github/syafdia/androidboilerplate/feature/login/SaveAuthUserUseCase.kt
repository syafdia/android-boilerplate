package com.github.syafdia.androidboilerplate.feature.login

import com.github.syafdia.androidboilerplate.core.Auth
import com.github.syafdia.androidboilerplate.data.model.User


class SaveAuthUserUseCase(private val auth: Auth) {

    fun execute(user: User) {
        auth.setUser(user)
    }
}