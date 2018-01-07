package com.github.syafdia.androidboilerplate.config

import android.content.Context
import android.support.v7.preference.PreferenceManager
import android.webkit.URLUtil
import com.github.syafdia.androidboilerplate.BuildConfig


object AppConfig {

    const val SQLITE_DB_NAME = BuildConfig.APPLICATION_ID + "-main.db"

    val DEBUG = BuildConfig.DEBUG

    var BASE_URL = BuildConfig.BASE_URL

    var SPLASH_TIME_OUT: Long = 3

    const val SHARED_PREFERENCE_FILE_KEY = BuildConfig.APPLICATION_ID + "-shared-pref"

    fun init(context: Context) {
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)

        BASE_URL = getBaseUrl(sharedPreference.getString(PreferenceKey.BASE_URL, ""))
        SPLASH_TIME_OUT = getSplashTimeOut(sharedPreference.getLong(PreferenceKey.SPLASH_TIME_OUT, 0))
    }

    private fun getBaseUrl(baseUrl: String): String {
        return if (URLUtil.isValidUrl(baseUrl)) baseUrl else BuildConfig.BASE_URL
    }

    private fun getSplashTimeOut(splashTimeOut: Long): Long {
        return if (splashTimeOut.toInt() !== 0) splashTimeOut else SPLASH_TIME_OUT
    }
}