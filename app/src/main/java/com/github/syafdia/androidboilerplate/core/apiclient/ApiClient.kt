package com.github.syafdia.androidboilerplate.core.apiclient

import com.github.syafdia.androidboilerplate.config.AppConfig
import com.github.syafdia.androidboilerplate.core.Auth
import io.reactivex.Maybe
import io.reactivex.Observable
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.Request


class ApiClient(private val okHttpClient: OkHttpClient, private val auth: Auth) {

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
    }

    fun post(route: String, data: Map<String, String>): Observable<ResponseBody> {
        val formBody = data.entries
                .fold(FormBody.Builder(), { acc, entry -> acc.add(entry.key, entry.value)})
                .build()

        val request = createRequestBuilder()
                .url("${AppConfig.BASE_URL}/$route")
                .post(formBody)
                .build()

        return call(request)
    }

    fun get(route: String, data: Map<String, String>?): Observable<ResponseBody> {
        val url = "${AppConfig.BASE_URL}/$route"
        val httpUrl = data?.entries
                ?.fold(
                        HttpUrl.parse(url)?.newBuilder(),
                        { acc, entry -> acc?.addQueryParameter(entry.key, entry.value)}
                )
                ?.build()

        val request = createRequestBuilder()
                .url(httpUrl)
                .build()

        return call(request)
    }

    fun gql(gqlPayload: String): Observable<ResponseBody> {
        return post(AppConfig.GRAPHQL_END_POINT, mapOf("query" to gqlPayload))
    }

    private fun createRequestBuilder(): Request.Builder {
        val requestBuilder = Request.Builder()

        auth.getUser().let {
            requestBuilder.addHeader(HEADER_AUTHORIZATION, "Bearer ${it?.token}")
        }

        return requestBuilder
    }

    private fun call(request: Request): Observable<ResponseBody> {
        return Observable
                .defer {
                    val response = okHttpClient.newCall(request).execute()

                    if (response.code() == 401) {
                        auth.deleteUser()
                        Observable.empty<ResponseBody>()
                    }

                    Observable.just(response.body())
                }
    }
}