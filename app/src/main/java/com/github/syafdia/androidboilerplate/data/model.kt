package com.github.syafdia.androidboilerplate.data.model

import android.arch.persistence.room.*
import com.github.syafdia.androidboilerplate.core.auth.AuthUser

enum class UserType {
    ROOT,
    ADMIN,
    USER,
}

@Entity(tableName = "users")
data class User(
        override val token: String,

        @PrimaryKey
        val id: String,
        val username: String,
        val fullName: String,
        val avatar: String,

        @TypeConverters(UserTypeConverter::class)
        val type: UserType
) : AuthUser

class UserTypeConverter {

    @TypeConverter
    fun toUserType(type: String): UserType {
        return UserType.valueOf(type)
    }

    @TypeConverter
    fun toString(type: UserType): String {
        return type.toString()
    }
}


