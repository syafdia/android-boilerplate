package com.github.syafdia.androidboilerplate.data.source.storage

import com.github.syafdia.androidboilerplate.core.Json
import com.github.syafdia.androidboilerplate.data.model.User
import io.reactivex.Observable
import io.reactivex.Single


class UserStorage(private val storage: Storage) {

    fun getUser(): Single<User> {
        val authUserStr = storage.get(StorageKey.AUTH_USER)

        if (authUserStr.isEmpty()) {
            return Observable.empty<User>().singleOrError()
        }

        return Single.just(Json.parseAs(User::class.java, authUserStr))
    }

    fun setUser(user: User) {
        storage.put(StorageKey.AUTH_USER, Json.stringify(user))
    }

    fun deleteUser() {
        storage.delete(StorageKey.AUTH_USER)
    }
}