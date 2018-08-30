package com.github.syafdia.androidboilerplate.data.source.room

import android.arch.persistence.room.*
import com.github.syafdia.androidboilerplate.data.model.UserEntity
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM users LIMIT 1")
    fun getUser(): Single<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUser(user: UserEntity)

    @Query("DELETE FROM users")
    fun deleteUser()
}