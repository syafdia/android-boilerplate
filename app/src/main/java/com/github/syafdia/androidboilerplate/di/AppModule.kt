package com.github.syafdia.androidboilerplate.di

import android.arch.persistence.room.Room
import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.syafdia.androidboilerplate.App
import com.github.syafdia.androidboilerplate.config.AppConfig
import com.github.syafdia.androidboilerplate.core.apiclient.ApiClient
import com.github.syafdia.androidboilerplate.core.Auth
import com.github.syafdia.androidboilerplate.core.Storage
import com.github.syafdia.androidboilerplate.core.provider.AndroidResourceProvider
import com.github.syafdia.androidboilerplate.core.provider.AndroidSchedulerProvider
import com.github.syafdia.androidboilerplate.core.provider.ResourceProvider
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import com.github.syafdia.androidboilerplate.data.source.room.AppDatabase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton


@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, AppConfig.SQLITE_DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    fun provideStorage(): Storage {
        return Storage(app.getSharedPreferences(
                AppConfig.SHARED_PREFERENCE_FILE_KEY,
                Context.MODE_PRIVATE
        ))
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()
    }

    @Provides
    @Singleton
    fun provideAuth(storage: Storage): Auth {
        return Auth(storage)
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return AndroidSchedulerProvider()
    }

    @Provides
    @Singleton
    fun provideResourceProvider(): ResourceProvider {
        return AndroidResourceProvider(app)
    }

    @Provides
    @Singleton
    fun provideApiClient(okHttpClient: OkHttpClient, auth: Auth): ApiClient {
        return ApiClient(okHttpClient, auth)
    }
}