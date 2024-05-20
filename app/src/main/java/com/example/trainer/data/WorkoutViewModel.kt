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
    private val workoutDao: WorkoutDao,
    private val userSession: UserSession
) : ViewModel() {

    private val _someLiveData = MutableLiveData<String>()
    val someLiveData: LiveData<String> get() = _someLiveData

    fun addCalories(date: String, calories: Int) {
        viewModelScope.launch {
            val username = userSession.getAuthUser() ?: return@launch
            val workout = workoutDao.getWorkoutByDateAndUser(date, username)
            if (workout != null) {
                workoutDao.updateCalories(date, username, workout.calories + calories)
            } else {
                workoutDao.insertWorkout(WorkoutEntity(date = date, calories = calories, username = username))
            }
            updateLiveData() // Оновити дані після додавання калорій
        }
    }

    fun updateLiveData() {
        viewModelScope.launch {
            val username = userSession.getAuthUser() ?: return@launch
            val data = workoutDao.getAllWorkoutsForUser(username).toString()
            _someLiveData.postValue(data)
        }
    }
}
