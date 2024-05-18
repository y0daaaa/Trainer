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

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query("DELETE FROM users WHERE username = :username")
    suspend fun delete(username: String)

    @Insert
    suspend fun insertTrainingSession(trainingSession: TrainingSession)

    @Query("SELECT * FROM training_sessions WHERE username = :username AND date BETWEEN :startDate AND :endDate")
    suspend fun getTrainingSessionsByDate(username: String, startDate: Long, endDate: Long): List<TrainingSession>
}
