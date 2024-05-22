package com.example.trainer.ui.signin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.trainer.R
import com.example.trainer.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding

    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect { state ->
                    state.errorMessage?.let { showSnack(it) }

                    if (state.authDone || state.isAuth) {
                        findNavController().navigate(R.id.action_global_profile_nav)
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        binding.apply {
            btnSignIn.setOnClickListener {
                if (checkFilledFields()) {
                    val username = binding.etUsername.text.toString().trim()
                    val password = binding.etPassword.text.toString().trim()
                    viewModel.signIn(username, password)
                    return@setOnClickListener
                }
                viewModel.showMessage("Fill empty forms")
            }
            tvSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_global_sign_up_screen)
            }
            btnFeedback.setOnClickListener {
                findNavController().navigate(R.id.action_global_feedback_screen)
            }
        }
    }

    private fun checkFilledFields(): Boolean {
        val usernameFilled = binding.etUsername.text.isNotBlank()
        val passwordFilled = binding.etPassword.text.isNotBlank()
        return usernameFilled && passwordFilled
    }

    private fun showSnack(message: String) {
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
        viewModel.userMessageShown()
    }
}
