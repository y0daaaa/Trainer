package com.example.trainer.ui.light

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trainer.R
import com.example.trainer.databinding.FragmentLight2Binding

class Light2Fragment : Fragment(R.layout.fragment_light2) {

    private lateinit var binding: FragmentLight2Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLight2Binding.bind(view)

        binding.lightEnd.setOnClickListener {
            findNavController().navigate(R.id.action_global_homeFragment)
        }
    }
}