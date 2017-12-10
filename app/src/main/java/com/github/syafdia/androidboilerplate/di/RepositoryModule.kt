package com.github.syafdia.androidboilerplate.di

import com.github.syafdia.androidboilerplate.data.repository.UserRepository
import com.github.syafdia.androidboilerplate.data.source.api.UserApi
import com.github.syafdia.androidboilerplate.data.source.room.UserRoom
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideUserRepository(userApi: UserApi, userRoom: UserRoom): UserRepository {
        return UserRepository(userApi, userRoom)
    }
}