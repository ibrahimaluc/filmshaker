package com.example.filmshakerkotlin.ui.screen.watched


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.filmshakerkotlin.R
import com.example.filmshakerkotlin.data.local.watched.WatchedDatabase
import com.example.filmshakerkotlin.data.local.watched.WatchedEntity
import com.example.filmshakerkotlin.databinding.FragmentWatchedBinding
import kotlinx.coroutines.launch


class WatchedFragment : Fragment() {

    private lateinit var binding: FragmentWatchedBinding
    private var watchedMovieAdapter: WatchedMovieAdapter? = null
    private var watchedList: ArrayList<WatchedEntity> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_watched, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWatchedBinding.bind(view)
        var emptyListView: View? = null

        val toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        val watchedDao = WatchedDatabase.getInstance(requireContext()).watchedDao()
        lifecycleScope.launch {
            watchedList = watchedDao.getWatchedMovies() as ArrayList<WatchedEntity>
            if (watchedList.isEmpty()) {
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

                watchedMovieAdapter = WatchedMovieAdapter(watchedList)
                binding.recyclerView.adapter = watchedMovieAdapter
            }
        }
    }

}