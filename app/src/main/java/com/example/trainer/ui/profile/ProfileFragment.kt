package com.example.trainer.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.trainer.R
import com.example.trainer.databinding.FragmentProfileBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // вихід
        requireActivity().onBackPressedDispatcher.addCallback {
            requireActivity().finish()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect { state ->
                    Log.d("ProfileFragment", "State: $state")
                    // показати помилку
                    state.errorMessage?.let {
                        showMessage(it)
                    }
                    // перекид на реєстрацію
                    if (state.userNotAuth || state.logoutDone) {
                        findNavController().navigate(R.id.action_global_sign_in_screen)
                    }
                    binding.tvEmail.text = state.email
                    binding.tvLogin.text = state.username
                    binding.tvAge.text = state.age
                    binding.tvHeight.text = state.height
                    binding.tvWeight.text = state.weight
                }
            }
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
        }

        binding.btnFeedbackProfile.setOnClickListener {
            findNavController().navigate(R.id.action_global_feedback_screen)
        }
    }

    private fun showMessage(message: String) {
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
        viewModel.userMessageShown()
    }
}
