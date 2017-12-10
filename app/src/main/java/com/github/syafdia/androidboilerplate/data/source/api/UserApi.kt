package com.github.syafdia.androidboilerplate.data.source.api

import com.github.syafdia.androidboilerplate.data.model.User
import io.reactivex.Flowable

class UserApi {

    fun authenticate(username: String, password: String): Flowable<User> {
        TODO()
    }
}