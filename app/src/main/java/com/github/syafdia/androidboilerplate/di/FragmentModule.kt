package com.github.syafdia.androidboilerplate.di

import com.github.syafdia.androidboilerplate.feature.login.LoginFragment
import com.github.syafdia.androidboilerplate.feature.login.LoginModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun bindLoginFragment(): LoginFragment
}