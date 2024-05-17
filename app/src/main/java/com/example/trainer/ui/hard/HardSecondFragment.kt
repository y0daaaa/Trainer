package com.example.trainer.ui.hard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trainer.R
import android.util.Log
import com.example.trainer.databinding.FragmentHardSecondBinding

class HardSecondFragment : Fragment(R.layout.fragment_hard_second) {

    private var _binding: FragmentHardSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHardSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.hardNext2.setOnClickListener {
            Log.d("LightFragment", "Button clicked")
            findNavController().navigate(R.id.action_global_hardThirdFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
