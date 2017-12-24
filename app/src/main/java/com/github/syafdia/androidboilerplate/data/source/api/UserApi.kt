package com.github.syafdia.androidboilerplate.data.source.api

import com.github.syafdia.androidboilerplate.core.apiclient.ApiClient
import com.github.syafdia.androidboilerplate.data.model.User
import com.github.syafdia.androidboilerplate.data.model.UserType
import io.reactivex.Single

class UserApi(private val apiClient: ApiClient) {

    fun authenticate(username: String, password: String): Single<User> {

        val authUser = User(
                id = "USR_001",
                username = "foo_bar",
                fullName = "Foo Bar Baz",
                avatar = "thisisanavatarurl",
                token = "thisisyourtoken",
                type = UserType.ROOT
        )

        return Single.just(authUser)
    }
}