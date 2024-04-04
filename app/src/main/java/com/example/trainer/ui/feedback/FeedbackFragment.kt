package com.example.trainer.ui.feedback

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trainer.R
import com.example.trainer.databinding.FragmentFeedbackBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class FeedbackFragment : Fragment(R.layout.fragment_feedback) {

    private lateinit var binding: FragmentFeedbackBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFeedbackBinding.bind(view)

        binding.emailFb.setOnClickListener {
            sendEmail(binding.emailFb.text.toString(), "Тема", "")
        }

        binding.phoneFb.setOnClickListener {
            dialPhoneNumber(binding.phoneFb.text.toString())
        }
        binding.btnBackFb.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }

        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }

    }

    private fun sendEmail(email: String, subject: String, message: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email")
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }

        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }
}
