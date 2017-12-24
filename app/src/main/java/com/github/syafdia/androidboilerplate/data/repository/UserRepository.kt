package com.github.syafdia.androidboilerplate.data.repository

import com.github.syafdia.androidboilerplate.data.model.User
import com.github.syafdia.androidboilerplate.data.source.api.UserApi
import com.github.syafdia.androidboilerplate.data.source.room.UserDao
import com.github.syafdia.androidboilerplate.data.source.storage.UserStorage
import io.reactivex.Single


class UserRepository(private val userApi: UserApi, private val userDao: UserDao, private val userStorage: UserStorage) {

    companion object {
        private val TAG = UserRepository.javaClass.name
    }

    fun authenticate(username: String, password: String): Single<User> {
        return userApi.authenticate(username, password)
    }

    fun getAuthenticated(): Single<User> {
        return userDao.getUser()
    }

    fun setAuthenticated(user: User): Single<Unit> {
        return Single.fromCallable{ userDao.setUser(user) }
    }

    fun deleteAuthenticated(): Single<Unit> {
        return Single.fromCallable{ userDao.deleteUser() }
    }
}