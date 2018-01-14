package com.github.syafdia.androidboilerplate.config

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.preference.PreferenceManager
import android.webkit.URLUtil
import com.github.syafdia.androidboilerplate.BuildConfig


object AppConfig {

    const val SQLITE_DB_NAME = BuildConfig.APPLICATION_ID + "-main.db"

    const val GRAPHQL_END_POINT = "graphql"

    val DEBUG = BuildConfig.DEBUG

    val BASE_URL: String
        get() {
            val baseUrl = sharedPreference?.getString(PreferenceKey.BASE_URL, "") ?: ""
            return if (URLUtil.isValidUrl(baseUrl)) baseUrl else BuildConfig.BASE_URL
        }

    val SPLASH_TIME_OUT: Long
        get() {
            val splashTimeOut = sharedPreference?.getString(PreferenceKey.SPLASH_TIME_OUT, "0") ?: "0"

            return if (splashTimeOut == "0") 5 else splashTimeOut.toLong()
        }

    const val SHARED_PREFERENCE_FILE_KEY = BuildConfig.APPLICATION_ID + "-shared-pref"

    private var sharedPreference: SharedPreferences? = null

    fun init(context: Context) {
        sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
    }
}