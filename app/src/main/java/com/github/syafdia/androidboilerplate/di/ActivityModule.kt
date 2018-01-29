package com.github.syafdia.androidboilerplate.di

import com.github.syafdia.androidboilerplate.feature.dashboard.DashboardActivity
import com.github.syafdia.androidboilerplate.feature.dashboard.DashboardModule
import com.github.syafdia.androidboilerplate.feature.information.InformationActivity
import com.github.syafdia.androidboilerplate.feature.login.LoginActivity
import com.github.syafdia.androidboilerplate.feature.login.LoginModule
import com.github.syafdia.androidboilerplate.feature.setting.SettingActivity
import com.github.syafdia.androidboilerplate.feature.splash.SplashActivity
import com.github.syafdia.androidboilerplate.feature.splash.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [DashboardModule::class])
    abstract fun bindDashboardActivity(): DashboardActivity

    @ContributesAndroidInjector()
    abstract fun bindSettingActivity(): SettingActivity

    @ContributesAndroidInjector()
    abstract fun bindInformationActivity(): InformationActivity
}