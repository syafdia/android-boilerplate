package com.github.syafdia.androidboilerplate.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object ObjUtil {

    fun objToMap(obj: Any): Map<String, Any> {
        val json = Gson().toJsonTree(obj)
        return Gson().fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)
    }
}