package com.github.syafdia.androidboilerplate.data.source.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.github.syafdia.androidboilerplate.data.model.User
import com.github.syafdia.androidboilerplate.data.model.UserTypeConverter

@Database(entities = [User::class], version = 1)
@TypeConverters(UserTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
}