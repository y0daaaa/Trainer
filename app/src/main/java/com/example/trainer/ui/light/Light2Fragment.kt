package com.example.trainer.ui.light

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import android.util.Log
import com.example.trainer.R
import com.example.trainer.databinding.FragmentLight2Binding
import com.example.trainer.data.WorkoutViewModel
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

        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        binding.lightEnd.setOnClickListener {
            Log.d("Light2Fragment", "Button clicked")
            workoutViewModel.addCalories(date, LIGHT_WORKOUT_CALORIES)
            findNavController().navigate(R.id.action_global_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val LIGHT_WORKOUT_CALORIES = 100
    }
}
