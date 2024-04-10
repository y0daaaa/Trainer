package com.example.trainer.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trainer.R
import com.example.trainer.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        binding.lightTablet.setOnClickListener {
            findNavController().navigate(R.id.action_global_lightFragment)
        }

        binding.mediumTablet.setOnClickListener {
            findNavController().navigate(R.id.action_global_mediumFragment)
        }

        binding.hardTablet.setOnClickListener {
            findNavController().navigate(R.id.action_global_hardFragment)
        }
    }
}
