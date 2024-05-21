package com.example.trainer.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainer.data.User
import com.example.trainer.data.UserSession
import com.example.trainer.data.UsersDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingUpViewModel @Inject constructor(
    private val userDao: UsersDao,
    private val session: UserSession
) : ViewModel() {
    companion object {
        const val TAG = "SingUpViewModel"
    }

    data class State(
        val errorMessage: String? = null,
        val signUpDone: Boolean = false,
    )

    private val _stateFlow = MutableStateFlow(State())
    val stateFlow = _stateFlow.asStateFlow()

    fun signUp(username: String, email: String, password: String, age: String, height: String, weight: String) {
        viewModelScope.launch {
            val user = userDao.fetchUser(username)
            if (user != null) {
                showMessage("Користувач з таким іменем уже інсує")
                return@launch
            }

            val newUser = User(username, email, password, age, height, weight)
            userDao.insert(newUser)
            session.saveAuthUser(newUser.username)
            _stateFlow.update { it.copy(signUpDone = true) }
        }
    }

    fun userMessageShown() {
        _stateFlow.update { it.copy(errorMessage = null) }
    }

    fun showMessage(message: String) {
        _stateFlow.update { it.copy(errorMessage = message) }
    }
}