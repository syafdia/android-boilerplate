package com.github.syafdia.androidboilerplate.core

import com.github.syafdia.androidboilerplate.data.model.User
import io.reactivex.subjects.PublishSubject


class Auth(private val storage: Storage) {

    companion object {
        private const val AUTH_USER = "AUTH_USER"
    }

    val subject: PublishSubject<Auth> = PublishSubject.create()

    fun isAuthenticated(): Boolean {
        return getUser() != null
    }

    fun setUser(user: User) {
        storage.put(AUTH_USER, Json.stringify(user))
        subject.onNext(this)
    }

    fun getUser(): User? {
        return Json.parseAs(User::class.java, storage.get(AUTH_USER))
    }

    fun deleteUser() {
        storage.delete(AUTH_USER)
        subject.onNext(this)
    }
}