package com.example.trainer.ui.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trainer.data.WorkoutEntity
import com.example.trainer.databinding.ItemStatisticsBinding

class StatisticsAdapter : ListAdapter<WorkoutEntity, StatisticsAdapter.StatisticsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsViewHolder {
        val binding = ItemStatisticsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatisticsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatisticsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StatisticsViewHolder(private val binding: ItemStatisticsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(workout: WorkoutEntity) {
            binding.textViewDate.text = workout.date
            binding.textViewCalories.text = workout.calories.toString()
            binding.textViewUsername.text = workout.username
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<WorkoutEntity>() {
        override fun areItemsTheSame(oldItem: WorkoutEntity, newItem: WorkoutEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WorkoutEntity, newItem: WorkoutEntity): Boolean {
            return oldItem == newItem
        }
    }
}
