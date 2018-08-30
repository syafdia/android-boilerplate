package com.github.syafdia.androidboilerplate.data.repository

import com.github.syafdia.androidboilerplate.data.model.UserEntity
import com.github.syafdia.androidboilerplate.data.source.api.UserApi
import io.reactivex.Single


class UserRepository(private val userApi: UserApi) {

    fun authenticate(username: String, password: String): Single<UserEntity> {
        return userApi.authenticate(username, password)
    }
}