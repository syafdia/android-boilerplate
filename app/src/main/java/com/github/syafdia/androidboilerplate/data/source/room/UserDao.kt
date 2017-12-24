package com.github.syafdia.androidboilerplate.data.source.room

import android.arch.persistence.room.*
import com.github.syafdia.androidboilerplate.data.model.User
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM users LIMIT 1")
    fun getUser(): Single<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUser(user: User)

    @Query("DELETE FROM users")
    fun deleteUser()
}