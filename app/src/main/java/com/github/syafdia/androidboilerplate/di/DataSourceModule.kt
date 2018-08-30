package com.github.syafdia.androidboilerplate.di

import com.github.syafdia.androidboilerplate.data.source.api.ApiClient
import com.github.syafdia.androidboilerplate.data.source.api.UserApi
import com.github.syafdia.androidboilerplate.data.source.room.AppDatabase
import com.github.syafdia.androidboilerplate.data.source.room.UserDao
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

}