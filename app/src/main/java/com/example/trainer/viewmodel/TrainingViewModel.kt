package com.example.trainer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainer.data.TrainingSession
import com.example.trainer.repository.TrainingRepository
import com.example.trainer.data.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val repository: TrainingRepository,
    val userSession: UserSession
) : ViewModel() {

    private val _trainingSessions = MutableLiveData<List<TrainingSession>>()
    val trainingSessions: LiveData<List<TrainingSession>> get() = _trainingSessions

    fun addTrainingSession(units: Int) {
        val username = userSession.getAuthUser() ?: return
        val currentDateMillis = System.currentTimeMillis()
        val trainingSession = TrainingSession(
            username = username,
            units = units,
            date = currentDateMillis
        )
        viewModelScope.launch {
            repository.insertTrainingSession(trainingSession)
        }
    }

    fun loadTrainingSessionsByDate(username: String, startDate: Long, endDate: Long) {
        viewModelScope.launch {
            val sessions = repository.getTrainingSessionsByDate(username, startDate, endDate)
            _trainingSessions.postValue(sessions)
        }
    }
}
