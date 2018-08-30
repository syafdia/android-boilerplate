package com.github.syafdia.androidboilerplate.feature.dashboard

import com.github.syafdia.androidboilerplate.core.Auth
import com.github.syafdia.androidboilerplate.core.provider.SchedulerProvider
import com.github.syafdia.androidboilerplate.feature.dashboard.domain.usecase.DeleteAuthUserUseCase
import com.github.syafdia.androidboilerplate.feature.dashboard.domain.usecase.GetAuthSubjectUseCase
import com.github.syafdia.androidboilerplate.feature.dashboard.domain.usecase.GetAuthUserUseCase
import com.github.syafdia.androidboilerplate.feature.dashboard.presentation.DashboardViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DashboardModule {

    @Provides
    fun provideGetAuthUserUseCase(auth: Auth): GetAuthUserUseCase {
        return GetAuthUserUseCase(auth)
    }

    @Provides
    fun provideDeleteAuthUserUseCase(auth: Auth): DeleteAuthUserUseCase {
        return DeleteAuthUserUseCase(auth)
    }

    @Provides
    fun provideGetAuthSubjectUseCase(auth: Auth): GetAuthSubjectUseCase {
        return GetAuthSubjectUseCase(auth)
    }

    @Provides
    fun provideDashboardViewModelFactory(
            getAuthUserUseCase: GetAuthUserUseCase,
            deleteAuthUserUseCase: DeleteAuthUserUseCase,
            getAuthSubjectUseCase: GetAuthSubjectUseCase,
            schedulerProvider: SchedulerProvider
    ): DashboardViewModelFactory {
        return DashboardViewModelFactory(
                getAuthUserUseCase,
                deleteAuthUserUseCase,
                getAuthSubjectUseCase,
                schedulerProvider
        )
    }
}