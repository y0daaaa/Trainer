package com.example.trainer.ui.medium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trainer.R
import android.util.Log
import com.example.trainer.databinding.FragmentMediumFirstBinding

class MediumFirstFragment : Fragment(R.layout.fragment_medium_first) {

    private var _binding: FragmentMediumFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMediumFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mediumNext1.setOnClickListener {
            Log.d("LightFragment", "Button clicked")
            findNavController().navigate(R.id.action_global_mediumSecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
