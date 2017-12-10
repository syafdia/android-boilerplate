package com.github.syafdia.androidboilerplate.data.model

enum class UserType {
    SUPER_ADMINISTRATOR,
    ADMINISTRATOR,
}

data class User(
        val username: String,
        val fullName: String,
        val token: String,
        val type: UserType
)


