package com.example.trainer.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.trainer.R
import com.example.trainer.viewmodel.TrainingViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class StatisticsFragment : Fragment() {

    private val trainingViewModel: TrainingViewModel by viewModels()
    private lateinit var statisticsTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_statistics, container, false)
        statisticsTextView = view.findViewById(R.id.statistics_text_view)

        val username = trainingViewModel.userSession.getAuthUser()
        if (username != null) {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            val startDate = calendar.timeInMillis
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            val endDate = calendar.timeInMillis

            trainingViewModel.loadTrainingSessionsByDate(username, startDate, endDate)

            trainingViewModel.trainingSessions.observe(viewLifecycleOwner, Observer { sessions ->
                val totalUnits = sessions.sumBy { it.units }
                statisticsTextView.text = "Total units for today: $totalUnits"
            })
        } else {
            statisticsTextView.text = "No authenticated user found"
        }

        return view
    }
}
