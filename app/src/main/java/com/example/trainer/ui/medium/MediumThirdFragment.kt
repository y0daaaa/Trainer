package com.example.trainer.ui.medium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import android.util.Log
import com.example.trainer.R
import com.example.trainer.databinding.FragmentMediumThirdBinding
import com.example.trainer.data.WorkoutViewModel // Додав правильний шлях імпорту
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class MediumThirdFragment : Fragment(R.layout.fragment_medium_second) {

    private var _binding: FragmentMediumThirdBinding? = null
    private val binding get() = _binding!!
    private val workoutViewModel: WorkoutViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMediumThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        binding.mediumEnd.setOnClickListener {
            Log.d("LightFragment", "Button clicked")
            workoutViewModel.addCalories(date, MEDIUM_WORKOUT_CALORIES)
            findNavController().navigate(R.id.action_global_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val MEDIUM_WORKOUT_CALORIES = 200
    }
}
