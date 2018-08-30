package com.github.syafdia.androidboilerplate.core

import android.annotation.SuppressLint
import android.content.SharedPreferences
import io.reactivex.Single


class Storage(private val sharedPreferences: SharedPreferences) {

    fun putAsync(key: String, value: String): Single<Unit> {
        return Single.fromCallable { put(key, value) }
    }

    fun getAsync(key: String): Single<String> {
        return Single.fromCallable { get(key) }
    }

    fun deleteAsync(key: String): Single<Unit> {
        return Single.fromCallable { delete(key) }
    }

    @SuppressLint("ApplySharedPref")
    private fun put(key: String, value: String) {
        sharedPreferences.edit()
                ?.putString(key, value)
                ?.commit()

    }

    private fun get(key: String): String {
        return sharedPreferences.getString(key, "")
    }

    @SuppressLint("ApplySharedPref")
    private fun delete(key: String) {
        sharedPreferences.edit()
                ?.remove(key)
                ?.commit()
    }
}