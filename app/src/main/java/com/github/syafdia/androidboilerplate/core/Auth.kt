package com.github.syafdia.androidboilerplate.core

import com.github.syafdia.androidboilerplate.data.model.User


class Auth(private val storage: Storage) {

    companion object {
        private const val AUTH_USER = "AUTH_USER"
    }

    var onUserDeleteListener: (() -> Unit)? = null

    fun isAuthenticated(): Boolean {
        return getUser() != null
    }

    fun setUser(user: User) {
        storage.put(AUTH_USER, Json.stringify(user))
    }

    fun getUser(): User? {
        return Json.parseAs(User::class.java, storage.get(AUTH_USER))
    }

    fun deleteUser() {
        storage.delete(AUTH_USER)
        onUserDeleteListener?.invoke()
    }
}