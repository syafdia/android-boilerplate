package com.github.syafdia.androidboilerplate.di

import com.github.syafdia.androidboilerplate.data.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideUserRepository(): UserRepository {
        return UserRepository()
    }


}