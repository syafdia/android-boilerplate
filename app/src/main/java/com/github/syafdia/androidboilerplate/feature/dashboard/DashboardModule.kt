package com.github.syafdia.androidboilerplate.feature.dashboard

import com.github.syafdia.androidboilerplate.core.Auth
import com.github.syafdia.androidboilerplate.core.provider.ResourceProvider
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import com.github.syafdia.androidboilerplate.data.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class DashboardModule {

    @Provides
    fun provideDashboardViewModelFactory(
            auth: Auth,
            resourceProvider: ResourceProvider,
            schedulerProvider: SchedulerProvider,
            userRepository: UserRepository
    ): DashboardViewModelFactory {

        return DashboardViewModelFactory(
                auth,
                resourceProvider,
                schedulerProvider,
                userRepository
        )
    }
}