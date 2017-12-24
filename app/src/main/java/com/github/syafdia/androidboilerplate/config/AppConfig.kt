package com.github.syafdia.androidboilerplate.config

import com.github.syafdia.androidboilerplate.BuildConfig


object AppConfig {

    const val SQLITE_DB_NAME = BuildConfig.APPLICATION_ID + "-main.db"

    const val SPLASH_TIME_OUT: Long = 3

    val BASE_URL = BuildConfig.BASE_URL

    val DEBUG = BuildConfig.DEBUG

    const val SHARED_PREFERENCE_FILE_KEY = BuildConfig.APPLICATION_ID + "-shared-pref"
}