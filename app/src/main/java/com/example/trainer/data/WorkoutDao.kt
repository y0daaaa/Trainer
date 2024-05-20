package com.example.trainer.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workoutentity WHERE date = :date AND username = :username")
    suspend fun getWorkoutByDateAndUser(date: String, username: String): WorkoutEntity?

    @Query("UPDATE workoutentity SET calories = :calories WHERE date = :date AND username = :username")
    suspend fun updateCalories(date: String, username: String, calories: Int)

    @Query("SELECT * FROM workoutentity WHERE username = :username")
    suspend fun getAllWorkoutsForUser(username: String): List<WorkoutEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workoutEntity: WorkoutEntity)
}
