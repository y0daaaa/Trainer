package com.example.trainer.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: WorkoutEntity)

    @Query("SELECT * FROM workout_table WHERE date = :date")
    suspend fun getWorkoutByDate(date: String): WorkoutEntity?

    @Query("UPDATE workout_table SET calories = :calories WHERE date = :date")
    suspend fun updateCalories(date: String, calories: Int)

    @Query("SELECT * FROM workout_table")
    suspend fun getAllWorkouts(): List<WorkoutEntity>
}
