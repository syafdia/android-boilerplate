package com.github.syafdia.androidboilerplate.data.model

import android.arch.persistence.room.*

enum class UserType {
    ROOT,
    ADMIN,
    USER,
}

@Entity(tableName = "users")
data class User(
        @PrimaryKey
        val id: String,
        val token: String,
        val username: String,
        val fullName: String,
        val avatar: String,

        @TypeConverters(UserTypeConverter::class)
        val type: UserType
)

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


