package com.example.trainer.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.trainer.databinding.FragmentStatisticsBinding
import com.example.trainer.data.WorkoutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment : Fragment() {

    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!
    private val workoutViewModel: WorkoutViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workoutViewModel.someLiveData.observe(viewLifecycleOwner) { data ->
            binding.statisticsContent.text = data // Оновлення тексту статистики
        }

        workoutViewModel.updateLiveData() // Виклик для оновлення даних
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
