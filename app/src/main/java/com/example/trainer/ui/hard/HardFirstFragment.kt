package com.example.trainer.ui.hard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trainer.R
import android.util.Log
import com.example.trainer.databinding.FragmentHardFirstBinding

class HardFirstFragment : Fragment(R.layout.fragment_hard_first) {

    private var _binding: FragmentHardFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHardFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.hardNext1.setOnClickListener {
            Log.d("LightFragment", "Button clicked")
            findNavController().navigate(R.id.action_global_hardSecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
