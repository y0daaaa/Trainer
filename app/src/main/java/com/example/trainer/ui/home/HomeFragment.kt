package com.example.trainer.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.trainer.R
import com.example.trainer.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect { state ->
                    // Показати помилку
                    state.errorMessage?.let {
                        showMessage(it)
                    }
                    // Перехід на екран авторизації, якщо користувач не авторизований
                    if (state.userNotAuth || state.logoutDone) {
                        findNavController().navigate(R.id.action_global_sign_in_screen)
                    }
                    // Відобразити дані користувача, якщо потрібно
                }
            }
        }

        binding.lightTablet.setOnClickListener {
            findNavController().navigate(R.id.action_global_lightFragment)
        }

        binding.mediumTablet.setOnClickListener {
            findNavController().navigate(R.id.action_global_mediumFirstFragment)
        }

        binding.hardTablet.setOnClickListener {
            findNavController().navigate(R.id.action_global_hardFirstFragment)
        }
    }

    private fun showMessage(message: String) {
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show() }
        viewModel.userMessageShown()
    }
}
