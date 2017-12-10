package com.github.syafdia.androidboilerplate.data.repository

import android.util.Log
import com.github.syafdia.androidboilerplate.data.model.User
import com.github.syafdia.androidboilerplate.data.source.api.UserApi
import com.github.syafdia.androidboilerplate.data.source.room.UserRoom
import io.reactivex.Flowable


class UserRepository(userApi: UserApi, userRoom: UserRoom) {

    val TAG = "UserRepository"

    fun authenticate(username: String, password: String): Flowable<User> {
        TODO()
    }

    fun getAuthenticated(): Flowable<User> {
        TODO()
    }
}