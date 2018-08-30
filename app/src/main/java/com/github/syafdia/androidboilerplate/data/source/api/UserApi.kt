package com.github.syafdia.androidboilerplate.data.source.api

import com.github.syafdia.androidboilerplate.data.model.UserEntity
import com.github.syafdia.androidboilerplate.data.model.UserEntityType
import io.reactivex.Single

class UserApi(private val apiClient: ApiClient) {

    fun authenticate(username: String, password: String): Single<UserEntity> {

        val authUser = UserEntity(
                id = "USR_001",
                username = "foo_bar",
                fullName = "Foo Bar Baz",
                avatar = "thisisanavatarurl",
                token = "thisisyourtoken",
                type = UserEntityType.ROOT
        )

        return Single.just(authUser)
    }
}