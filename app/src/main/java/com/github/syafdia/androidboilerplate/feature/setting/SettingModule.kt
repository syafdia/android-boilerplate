package com.github.syafdia.androidboilerplate.feature.setting

import com.github.syafdia.androidboilerplate.core.auth.Auth
import com.github.syafdia.androidboilerplate.core.provider.ResourceProvider
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class SettingModule {

    @Provides
    fun provideSettingViewModelFactory(
            auth: Auth,
            resourceProvider: ResourceProvider,
            schedulerProvider: SchedulerProvider
    ): SettingViewModelFactory {
        return SettingViewModelFactory(auth, resourceProvider, schedulerProvider)
    }
}