package com.github.syafdia.androidboilerplate.data.source.storage

import android.content.SharedPreferences


class Storage(private val sharedPreferences: SharedPreferences) {

    fun put(key: String, value: String) {
        sharedPreferences.edit()
                ?.putString(key, value)
                ?.commit()

    }

    fun get(key: String): String {
        return sharedPreferences.getString(key, "")
    }

    fun delete(key: String) {
        sharedPreferences.edit()
                ?.clear()
                ?.commit()
    }
}