package com.example.trainer.ui.light

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trainer.R
import com.example.trainer.databinding.FragmentLightBinding

class LightFragment : Fragment(R.layout.fragment_light) {

    private lateinit var binding: FragmentLightBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLightBinding.bind(view)

        binding.lightNext1.setOnClickListener {
            findNavController().navigate(R.id.action_global_light1Fragment)
        }
    }
}