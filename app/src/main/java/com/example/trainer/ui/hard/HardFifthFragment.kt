package com.example.trainer.ui.hard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import android.util.Log
import com.example.trainer.R
import com.example.trainer.databinding.FragmentHardFifthBinding
import com.example.trainer.data.WorkoutViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HardFifthFragment : Fragment(R.layout.fragment_hard_fifth) {

    private var _binding: FragmentHardFifthBinding? = null
    private val binding get() = _binding!!
    private val workoutViewModel: WorkoutViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHardFifthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        binding.hardEnd.setOnClickListener {
            Log.d("HardFifthFragment", "Button clicked")
            workoutViewModel.addCalories(date, HARD_WORKOUT_CALORIES)
            findNavController().navigate(R.id.action_global_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val HARD_WORKOUT_CALORIES = 300
    }
}
