package com.github.syafdia.androidboilerplate.di

import com.github.syafdia.androidboilerplate.data.repository.UserRepository
import com.github.syafdia.androidboilerplate.data.source.api.UserApi
import com.github.syafdia.androidboilerplate.data.source.room.UserDao
import com.github.syafdia.androidboilerplate.data.source.storage.UserStorage
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideUserRepository(userApi: UserApi, userDao: UserDao, userStorage: UserStorage): UserRepository {
        return UserRepository(userApi, userDao, userStorage)
    }
}