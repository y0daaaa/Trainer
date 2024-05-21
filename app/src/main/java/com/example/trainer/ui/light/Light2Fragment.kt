package com.example.trainer.ui.light

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.trainer.R
import com.example.trainer.data.WorkoutViewModel
import com.example.trainer.databinding.FragmentLight2Binding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class Light2Fragment : Fragment(R.layout.fragment_light2) {

    private var _binding: FragmentLight2Binding? = null
    private val binding get() = _binding!!
    private val workoutViewModel: WorkoutViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLight2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lightEnd.setOnClickListener {
            val date = getCurrentDate()
            workoutViewModel.addCalories(date, LIGHT_WORKOUT_CALORIES)
            findNavController().navigate(R.id.action_global_homeFragment)
        }
    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val LIGHT_WORKOUT_CALORIES = 370
    }
}
