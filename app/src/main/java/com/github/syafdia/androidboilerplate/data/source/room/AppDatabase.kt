package com.github.syafdia.androidboilerplate.data.source.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.github.syafdia.androidboilerplate.data.model.UserEntity
import com.github.syafdia.androidboilerplate.data.model.UserEntityTypeConverter

@Database(entities = [UserEntity::class], version = 1)
@TypeConverters(UserEntityTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
}