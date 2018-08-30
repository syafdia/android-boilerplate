package com.github.syafdia.androidboilerplate.data.model

import android.arch.persistence.room.*

enum class UserEntityType {
    ROOT,
    ADMIN,
    USER,
}

@Entity(tableName = "users")
data class UserEntity(
        @PrimaryKey
        val id: String,
        val token: String,
        val username: String,
        val fullName: String,
        val avatar: String,

        @TypeConverters(UserEntityTypeConverter::class)
        val type: UserEntityType
)

class UserEntityTypeConverter {

    @TypeConverter
    fun toUserType(type: String): UserEntityType {
        return UserEntityType.valueOf(type)
    }

    @TypeConverter
    fun toString(type: UserEntityType): String {
        return type.toString()
    }
}




