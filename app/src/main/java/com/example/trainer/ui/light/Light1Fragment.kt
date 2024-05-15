package com.example.trainer.ui.light

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trainer.R
import com.example.trainer.databinding.FragmentLight1Binding

class Light1Fragment : Fragment(R.layout.fragment_light1) {

    private lateinit var binding: FragmentLight1Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLight1Binding.bind(view)

        binding.lightNext2.setOnClickListener {
            findNavController().navigate(R.id.action_global_light2Fragment)
        }
    }
}