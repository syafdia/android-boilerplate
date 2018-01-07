package com.github.syafdia.androidboilerplate.core

import com.github.syafdia.androidboilerplate.data.model.User
import io.reactivex.Maybe
import io.reactivex.subjects.BehaviorSubject


class Auth {

    val userSubject: BehaviorSubject<Maybe<User>> = BehaviorSubject.create<Maybe<User>>()

    fun isAuthenticated(): Boolean {
        return getUser() != null
    }

    fun getUser(): User? {
        return userSubject.value?.blockingGet()
    }

    fun logOut() {
        userSubject.onNext(Maybe.empty())
    }
}