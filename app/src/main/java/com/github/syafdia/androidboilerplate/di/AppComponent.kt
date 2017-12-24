package com.github.syafdia.androidboilerplate.di

import com.github.syafdia.androidboilerplate.App
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            DataSourceModule::class,
            RepositoryModule::class,
            ActivityModule::class,
            FragmentModule::class
        ]
)
interface AppComponent {

    fun inject(app: App)
}