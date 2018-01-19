package com.github.syafdia.androidboilerplate

import android.app.Activity
import android.app.Application
import android.util.Log
import com.facebook.stetho.Stetho
import com.github.syafdia.androidboilerplate.core.CrashHandler
import com.github.syafdia.androidboilerplate.config.AppConfig
import com.github.syafdia.androidboilerplate.core.Json
import com.github.syafdia.androidboilerplate.di.AppModule
import com.github.syafdia.androidboilerplate.di.DaggerAppComponent
import com.github.syafdia.androidboilerplate.di.DataSourceModule
import com.github.syafdia.androidboilerplate.di.RepositoryModule
import com.google.gson.FieldNamingPolicy
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject


class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }

            CrashHandler.log(priority, tag?: "", message)

            if (throwable != null) {
                if (priority == Log.ERROR) {
                    CrashHandler.logError(throwable)
                } else if (priority == Log.WARN) {
                    CrashHandler.logWarning(throwable)
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        AppConfig.init(this)
        JodaTimeAndroid.init(this)

        if (AppConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            Timber.plant(DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }

        initDagger()
        Json.defaultFieldNamingPolicy = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    private fun initDagger() {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .dataSourceModule(DataSourceModule())
                .repositoryModule(RepositoryModule())
                .build()
                .inject(this)
    }
}