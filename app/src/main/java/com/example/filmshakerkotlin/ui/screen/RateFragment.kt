package com.example.filmshakerkotlin.ui.screen

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.filmshakerkotlin.R
import com.example.filmshakerkotlin.databinding.RateCardBinding

class RateFragment : DialogFragment() {
    private lateinit var binding: RateCardBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DataBindingUtil.inflate(
            requireActivity().layoutInflater,
            R.layout.rate_card,
            null,
            false
        )
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        seekBar()
        postRate()

        return builder.create()
    }

    private fun seekBar() {
        val ratingBar = binding.ratingBar
        val seekBar = binding.seekBar
        val progressText = binding.progressText
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val rating = progress / 10f
                ratingBar.rating = rating
                progressText.text = (rating).toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun postRate() {
        binding.rateButton.setOnClickListener {
            dismiss()
            Toast.makeText(requireContext(), "Your review has been sent.", Toast.LENGTH_SHORT)
                .show()
        }
    }


}
