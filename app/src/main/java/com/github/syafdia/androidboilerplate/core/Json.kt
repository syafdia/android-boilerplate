package com.github.syafdia.androidboilerplate.core

import com.google.gson.*


object Json {

    fun <T> parseAs(clazz: Class<T>, json: String): T? {
        return try {
            createGsonInstance().fromJson(json, clazz)
        } catch (e: Exception) {
            null
        }

    }

    fun parse(json: String): JsonObject {
        val jsonObject = JsonParser().parse(json).asJsonObject

        return jsonObject ?: JsonObject()
    }

    fun <T : Any> stringify(t: T): String {
        return createGsonInstance().toJson(t)
    }

    private fun createGsonInstance(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }
}