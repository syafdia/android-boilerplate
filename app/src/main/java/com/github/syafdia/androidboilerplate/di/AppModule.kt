package com.github.syafdia.androidboilerplate.di

import android.content.Context
import com.github.syafdia.androidboilerplate.App
import dagger.Binds
import dagger.Module


@Module
abstract class AppModule(private val app: App) {

    @Binds
    abstract fun bindContext(app: App): Context
}