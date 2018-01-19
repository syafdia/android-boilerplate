package com.github.syafdia.androidboilerplate.core

import com.github.salomonbrys.kotson.jsonArray
import com.google.gson.*


object Json {

    var defaultFieldNamingPolicy: FieldNamingPolicy = FieldNamingPolicy.IDENTITY

    fun <T> parseAs(clazz: Class<T>, jsonStr: String, fieldNamingPolicy: FieldNamingPolicy? = null): T? {
        return try {
            createGsonInstance(fieldNamingPolicy).fromJson(jsonStr, clazz)
        } catch (e: Exception) {
            null
        }
    }

    fun <T> parseAsListOf(clazz: Class<T>,
                          json: String,
                          fieldNamingPolicy: FieldNamingPolicy? = null
    ): List<T> {
        val gson = createGsonInstance(fieldNamingPolicy)

        return try {
            parseAsJsonArray(json).map { gson.fromJson(it, clazz) }
        } catch (e: Exception) {
            listOf()
        }
    }

    fun parseAsJsonObject(json: String): JsonObject? {
        return try {
            JsonParser().parse(json).asJsonObject
        } catch (e: Exception) {
            null
        }
    }

    fun parseAsJsonArray(json: String): JsonArray {
        return try {
            JsonParser().parse(json).asJsonArray
        } catch (e: Exception) {
            jsonArray()
        }
    }

    fun <T : Any> stringify(t: T, fieldNamingPolicy: FieldNamingPolicy? = null): String {
        return createGsonInstance(fieldNamingPolicy).toJson(t)
    }

    private fun createGsonInstance(fieldNamingPolicy: FieldNamingPolicy? = null): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(fieldNamingPolicy ?: defaultFieldNamingPolicy)
                .create()
    }
}