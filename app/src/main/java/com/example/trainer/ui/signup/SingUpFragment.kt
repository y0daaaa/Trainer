package com.example.trainer.ui.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.trainer.R
import com.example.trainer.databinding.FragmentSingUpBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SingUpFragment : Fragment(R.layout.fragment_sing_up) {

    private lateinit var binding: FragmentSingUpBinding

    private val viewModel by viewModels<SingUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect { state ->
                    // если ошибка то показать ошибку, затем сбросить состояние
                    state.errorMessage?.let {
                        showMessage(message = it)

                    }
                    if (state.signUpDone) {
                        // регистрация пройдена, переходим к экрану профиля
                        findNavController().navigate(R.id.action_global_profile_screen)
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSingUpBinding.bind(view)

        binding.btnSignUp.setOnClickListener {
            if (checkFilledFields()) {
                val username = binding.etUsername.text.toString().trim()
                val email = binding.etEmail.text.toString().trim()
                val password = binding.etPassword.text.toString().trim()
                val age = binding.etAge.text.toString().trim()
                val height = binding.etHeight.text.toString().trim()
                val weight = binding.etWeight.text.toString().trim()
                viewModel.signUp(username, email, password, age, height, weight)
                return@setOnClickListener
            }
            viewModel.showMessage("Заполните пустые поля")
        }

        binding.btnFeedbackSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_global_feedback_screen)
        }

        binding.gotoSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_global_sign_in_screen)
        }
    }

    // проверить на заполненость полей
    private fun checkFilledFields(): Boolean {
        val usernameFilled = binding.etUsername.text.isNotBlank()
        val emailFilled = binding.etEmail.text.isNotBlank()
        val passwordFilled = binding.etPassword.text.isNotBlank()
        val ageFilled = binding.etAge.text.isNotBlank()
        val heightFilled = binding.etHeight.text.isNotBlank()
        val weightFilled = binding.etWeight.text.isNotBlank()

        return usernameFilled && emailFilled && passwordFilled
                && ageFilled && heightFilled && weightFilled
    }

    private fun showMessage(message: String) {
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
        viewModel.userMessageShown()
    }
}