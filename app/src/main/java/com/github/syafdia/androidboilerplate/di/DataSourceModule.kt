package com.github.syafdia.androidboilerplate.di

import com.github.syafdia.androidboilerplate.core.apiclient.ApiClient
import com.github.syafdia.androidboilerplate.data.source.api.UserApi
import com.github.syafdia.androidboilerplate.data.source.room.AppDatabase
import com.github.syafdia.androidboilerplate.data.source.room.UserDao
import com.github.syafdia.androidboilerplate.data.source.storage.Storage
import com.github.syafdia.androidboilerplate.data.source.storage.UserStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun provideUserApi(apiClient: ApiClient): UserApi {
        return UserApi(apiClient)
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideUserStorage(storage: Storage): UserStorage {
        return UserStorage(storage)
    }

}