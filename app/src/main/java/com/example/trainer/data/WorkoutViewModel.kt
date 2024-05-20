package com.example.trainer.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val workoutDao: WorkoutDao
) : ViewModel() {

    private val _someLiveData = MutableLiveData<String>()
    val someLiveData: LiveData<String> get() = _someLiveData

    fun addCalories(date: String, calories: Int) {
        viewModelScope.launch {
            val workout = workoutDao.getWorkoutByDate(date)
            if (workout != null) {
                workoutDao.updateCalories(date, workout.calories + calories)
            } else {
                workoutDao.insertWorkout(WorkoutEntity(date, calories))
            }
            updateLiveData() // Оновлення даних після додавання калорій
        }
    }

    fun updateLiveData() {
        viewModelScope.launch {
            val data = workoutDao.getAllWorkouts().toString()
            _someLiveData.postValue(data)
        }
    }
}
