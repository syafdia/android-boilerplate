package com.github.syafdia.androidboilerplate.data.source.api

import com.github.syafdia.androidboilerplate.config.AppConfig
import com.github.syafdia.androidboilerplate.core.Auth
import com.github.syafdia.androidboilerplate.data.model.UserEntity
import io.reactivex.Single
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request


class ApiClient(private val okHttpClient: OkHttpClient, private val auth: Auth) {

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
    }

    fun post(route: String, data: Map<String, String>): Single<String> {
        return createRequestBuilder()
                .map { requestBuilder ->
                    val formBody = data.entries
                            .fold(FormBody.Builder()) { acc, entry -> acc.add(entry.key, entry.value)}
                            .build()

                    requestBuilder
                            .url("${AppConfig.BASE_URL}/$route")
                            .post(formBody)
                            .build()
                }
                .flatMap { call(it) }
    }

    fun get(route: String, data: Map<String, String>?): Single<String> {
        return createRequestBuilder()
                .map { requestBuilder ->
                    val url = "${AppConfig.BASE_URL}/$route"
                    val httpUrl = data?.entries
                            ?.fold(HttpUrl.parse(url)?.newBuilder()) {
                                acc, entry -> acc?.addQueryParameter(entry.key, entry.value)
                            }
                            ?.build()

                    requestBuilder
                            .url(httpUrl)
                            .build()

                }
                .flatMap { call(it) }
    }

    private fun createRequestBuilder(): Single<Request.Builder> {
        val requestBuilder = Request.Builder()

        return auth.getDataAs(UserEntity::class)
                .map { requestBuilder.addHeader(HEADER_AUTHORIZATION, "Bearer ${it.token}") }
                .onErrorReturn { requestBuilder }
    }

    private fun call(request: Request): Single<String> {
        val response = okHttpClient.newCall(request).execute()

        if (response.code() == 401 || response.code() == 403) {
            return auth.deleteData().map {
                throw ApiClientResponseErrorException("HTTP error ${response.code()} \"${response.message()}\"")
            }
        }

        val responseBody = response.body()
                ?.string()
                ?: throw ApiClientResponseErrorException("No response body from server")

        return Single.just(responseBody)
    }
}