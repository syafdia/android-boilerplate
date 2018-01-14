package com.github.syafdia.androidboilerplate.feature.dashboard

import com.github.syafdia.androidboilerplate.core.Auth
import dagger.Module
import dagger.Provides

@Module
class DashboardModule {

    @Provides
    fun provideDashboardViewModelFactory(auth: Auth): DashboardViewModelFactory {

        return DashboardViewModelFactory(auth)
    }
}