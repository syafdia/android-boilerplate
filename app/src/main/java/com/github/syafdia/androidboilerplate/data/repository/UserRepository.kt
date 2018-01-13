package com.github.syafdia.androidboilerplate.data.repository

import com.github.syafdia.androidboilerplate.data.model.User
import com.github.syafdia.androidboilerplate.data.source.api.UserApi
import io.reactivex.Single


class UserRepository(private val userApi: UserApi) {

    companion object {
        private val TAG = UserRepository.javaClass.name
    }

    fun authenticate(username: String, password: String): Single<User> {
        return userApi.authenticate(username, password)
    }
}