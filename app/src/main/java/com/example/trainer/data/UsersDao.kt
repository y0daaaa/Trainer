package com.example.trainer.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsersDao {

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun fetchUser(username: String): User?

    @Insert
    suspend fun insert(user: User)

    @Query("DELETE FROM users WHERE username = :username")
    suspend fun delete(username: String)

}