package com.github.syafdia.androidboilerplate.di

import com.github.syafdia.androidboilerplate.App
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import dagger.android.AndroidInjector



@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            ActivityModule::class,
            RepositoryModule::class
        ]
)
interface AppComponent {

    fun inject(app: App)
}