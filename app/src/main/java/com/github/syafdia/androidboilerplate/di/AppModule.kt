package com.github.syafdia.androidboilerplate.di

import android.content.Context
import com.github.syafdia.androidboilerplate.App
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton




@Module
class AppModule(private val app: App) {

    @Provides
    fun provideContext(): Context {
        return app.baseContext
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient()
    }
}