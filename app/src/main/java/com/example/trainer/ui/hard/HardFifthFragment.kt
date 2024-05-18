package com.example.trainer.ui.hard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.trainer.R
import com.example.trainer.databinding.FragmentHardFifthBinding
import com.example.trainer.viewmodel.TrainingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HardFifthFragment : Fragment() {

    private val trainingViewModel: TrainingViewModel by viewModels()
    private var _binding: FragmentHardFifthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHardFifthBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.hardEnd.setOnClickListener {
            // Додавання 100 одиниць до бази даних
            trainingViewModel.addTrainingSession(100)
            // Навігація до HomeFragment
            findNavController().navigate(R.id.action_global_homeFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
