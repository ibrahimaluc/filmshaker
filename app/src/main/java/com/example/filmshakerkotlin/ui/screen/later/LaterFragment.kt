package com.example.filmshakerkotlin.ui.screen.later

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.filmshakerkotlin.R
import com.example.filmshakerkotlin.data.local.later.LaterDatabase
import com.example.filmshakerkotlin.data.local.later.LaterEntity
import com.example.filmshakerkotlin.databinding.FragmentLaterBinding
import com.example.filmshakerkotlin.ui.screen.watched.WatchedMovieAdapter
import kotlinx.coroutines.launch

class LaterFragment : Fragment() {
    private lateinit var binding: FragmentLaterBinding
    private var laterMovieAdapter: LaterMovieAdapter? = null
    private var laterList: ArrayList<LaterEntity> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_later, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var emptyListView: View? = null

        binding = FragmentLaterBinding.bind(view)

        val toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        val laterDao = LaterDatabase.getInstance(requireContext()).laterDao()
        lifecycleScope.launch {
            laterList = laterDao.getLaterMovies() as ArrayList<LaterEntity>
            if (laterList.isEmpty()) {
                if (emptyListView == null) {
                    emptyListView = LayoutInflater.from(requireContext()).inflate(
                        R.layout.item_empty_list,
                        binding.cL,
                        false
                    )
                    binding.cL.addView(emptyListView)
                }
                binding.recyclerView.visibility = View.GONE
            } else {
                if (emptyListView != null) {
                    binding.cL.removeView(emptyListView)
                    emptyListView = null
                }
                binding.recyclerView.visibility = View.VISIBLE

                laterMovieAdapter = LaterMovieAdapter(laterList)
                binding.recyclerView.adapter = laterMovieAdapter
            }

        }
    }
}