package com.github.syafdia.androidboilerplate.feature.dashboard

import com.github.syafdia.androidboilerplate.core.Auth

class DeleteAuthUserUseCase(private val auth: Auth) {

    fun execute() {
        auth.deleteUser()
    }
}