package com.github.syafdia.androidboilerplate.di

import com.github.syafdia.androidboilerplate.feature.information.InformationFragment
import com.github.syafdia.androidboilerplate.feature.information.InformationModule
import com.github.syafdia.androidboilerplate.feature.login.LoginFragment
import com.github.syafdia.androidboilerplate.feature.login.LoginModule
import com.github.syafdia.androidboilerplate.feature.setting.SettingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindLoginFragment(): LoginFragment

    @ContributesAndroidInjector()
    abstract fun bindSettingFragment(): SettingFragment

    @ContributesAndroidInjector(modules = [InformationModule::class])
    abstract fun bindInformationFragment(): InformationFragment
}