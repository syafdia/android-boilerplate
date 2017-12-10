package com.github.syafdia.androidboilerplate.di

import com.github.syafdia.androidboilerplate.feature.login.LoginActivity
import com.github.syafdia.androidboilerplate.feature.login.LoginModule
import dagger.Module
import com.github.syafdia.androidboilerplate.feature.splash.SplashActivity
import com.github.syafdia.androidboilerplate.feature.splash.SplashModule
import dagger.android.ContributesAndroidInjector



@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindLoginActivity(): LoginActivity
}