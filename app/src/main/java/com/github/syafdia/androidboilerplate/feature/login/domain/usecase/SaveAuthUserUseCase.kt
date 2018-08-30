package com.github.syafdia.androidboilerplate.feature.login.domain.usecase

import com.github.syafdia.androidboilerplate.core.Auth
import com.github.syafdia.androidboilerplate.core.Json
import com.github.syafdia.androidboilerplate.data.model.UserEntity
import io.reactivex.Single


class SaveAuthUserUseCase(private val auth: Auth) {

    fun execute(user: UserEntity): Single<Unit> {
        return auth.setData(Json.stringify(user))
    }
}