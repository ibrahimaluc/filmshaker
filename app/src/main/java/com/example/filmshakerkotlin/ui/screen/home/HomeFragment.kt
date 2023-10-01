package com.example.filmshakerkotlin.ui.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.filmshakerkotlin.R
import com.example.filmshakerkotlin.core.adapter.ShakeListener
import com.example.filmshakerkotlin.core.util.ShakeControl
import com.example.filmshakerkotlin.databinding.FragmentHomeBinding
import com.example.filmshakerkotlin.ui.screen.poster.PosterFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var shakeListener: ShakeListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)
        shakeHome()
    }

    private fun shakeHome() {
        shakeListener = ShakeListener(requireContext()) {
            if (!ShakeControl.isShaked) {
                shakeListener.startListening()
                findNavController().navigate(R.id.action_homeFragment_to_posterFragment)
                ShakeControl.isShaked = true
                shakeListener.stopListening()
            }

        }
    }


}