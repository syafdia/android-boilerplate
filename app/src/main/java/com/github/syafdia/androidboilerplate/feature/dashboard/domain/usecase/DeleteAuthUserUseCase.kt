package com.github.syafdia.androidboilerplate.feature.dashboard.domain.usecase

import com.github.syafdia.androidboilerplate.core.Auth
import io.reactivex.Single

class DeleteAuthUserUseCase(private val auth: Auth) {

    fun execute(): Single<Unit> {
        return auth.deleteData()
    }
}