package com.example.trainer.repository

import com.example.trainer.data.TrainingSession
import com.example.trainer.data.UsersDao
import javax.inject.Inject

class TrainingRepository @Inject constructor(private val usersDao: UsersDao) {

    suspend fun insertTrainingSession(trainingSession: TrainingSession) {
        usersDao.insertTrainingSession(trainingSession)
    }

    suspend fun getTrainingSessionsByDate(username: String, startDate: Long, endDate: Long): List<TrainingSession> {
        return usersDao.getTrainingSessionsByDate(username, startDate, endDate)
    }
}
