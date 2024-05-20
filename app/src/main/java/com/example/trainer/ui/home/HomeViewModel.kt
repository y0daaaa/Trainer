package com.example.trainer.ui.home

import androidx.lifecycle.ViewModel
import com.example.trainer.data.UserSession
import com.example.trainer.data.UsersDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userDao: UsersDao,
    private val session: UserSession
) : ViewModel() {
    companion object {
        const val TAG = "HomeViewModel"
    }
}

