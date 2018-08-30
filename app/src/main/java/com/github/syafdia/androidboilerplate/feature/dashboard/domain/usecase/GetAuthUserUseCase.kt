package com.github.syafdia.androidboilerplate.feature.dashboard.domain.usecase

import com.github.syafdia.androidboilerplate.core.Auth
import com.github.syafdia.androidboilerplate.data.model.UserEntity
import io.reactivex.Single

class GetAuthUserUseCase(private val auth: Auth) {

    fun execute(): Single<UserEntity> {
        return auth.getDataAs(UserEntity::class)
    }
}