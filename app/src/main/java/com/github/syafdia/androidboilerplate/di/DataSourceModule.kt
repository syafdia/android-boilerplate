package com.github.syafdia.androidboilerplate.di

import com.github.syafdia.androidboilerplate.data.source.api.UserApi
import com.github.syafdia.androidboilerplate.data.source.room.UserRoom
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun provideUserApi(): UserApi = UserApi()

    @Provides
    @Singleton
    fun provideUserRoom(): UserRoom = UserRoom()

}