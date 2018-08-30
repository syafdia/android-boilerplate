package com.github.syafdia.androidboilerplate.core

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.toMap
import com.google.gson.*
import kotlin.reflect.KClass


object Json {

    fun <T: Any> parseAs(
            clazz: KClass<T>,
            jsonStr: String,
            fieldNamingPolicy: FieldNamingPolicy? = null
    ): T? {
        return parseAs(clazz.java, jsonStr, fieldNamingPolicy)
    }

    fun <T> parseAs(
            clazz: Class<T>,
            jsonStr: String,
            fieldNamingPolicy: FieldNamingPolicy? = null
    ): T? {
        return try {
            createGsonInstance(fieldNamingPolicy).fromJson(jsonStr, clazz)
        } catch (e: Exception) {
            null
        }
    }

    fun <T> parseAsListOf(
            clazz: Class<T>,
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

    fun <K, V> parseAsMapOf(
            json: String,
            fieldNamingPolicy: FieldNamingPolicy? = null
    ): Map<K, V> {
        return try {
            parseAsJsonObject(json)?.toMap()
                    ?.map { (it.key as K) to (it.value as V) }
                    ?.toMap()
                    ?: mapOf()
        } catch (e: Exception) {
            mapOf()
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

    fun <T : Any> stringifyWithNull(t: T, fieldNamingPolicy: FieldNamingPolicy? = null): String {
        return GsonBuilder()
                .setFieldNamingPolicy(fieldNamingPolicy ?: FieldNamingPolicy.IDENTITY)
                .serializeNulls()
                .create()
                .toJson(t)
    }

    private fun createGsonInstance(fieldNamingPolicy: FieldNamingPolicy? = null): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(fieldNamingPolicy ?: FieldNamingPolicy.IDENTITY)
                .create()
    }
}