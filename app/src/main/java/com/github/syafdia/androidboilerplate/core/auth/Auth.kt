package com.github.syafdia.androidboilerplate.core.auth

import io.reactivex.Maybe
import io.reactivex.subjects.BehaviorSubject


class Auth {

    val userSubject = BehaviorSubject.create<Maybe<AuthUser>>()

    fun isAuthenticated(): Boolean {
        return userSubject.hasValue()
    }

    fun hasAuthorization(authorization: Authorization): Boolean {
        TODO("Add authorization type, and check based on user")
    }

    fun logOut() {
        userSubject.onNext(Maybe.empty())
    }
}