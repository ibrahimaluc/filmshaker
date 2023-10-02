package com.example.filmshakerkotlin.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.filmshakerkotlin.R
import com.example.filmshakerkotlin.databinding.ItemResultCardBinding
import com.example.filmshakerkotlin.domain.model.MovieDetail

class NowPlayingMovieAdapter(
    private val nowPlayingList: ArrayList<MovieDetail>,
    private val fragmentManager: FragmentManager
) :
    RecyclerView.Adapter<NowPlayingMovieAdapter.NowPlayingViewHolder>() {
    class NowPlayingViewHolder(val binding: ItemResultCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemResultCardBinding>(
            inflater,
            R.layout.item_result_card,
            parent,
            false
        )
        return NowPlayingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nowPlayingList.size
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.binding.soon = nowPlayingList[position]

        holder.binding.soon?.let { movieDetail ->
            holder.binding.afisImageButton.setOnClickListener {
                val selectedMovieId: Int? = movieDetail.id
                if (selectedMovieId != null) {
                    val b = Bundle()
                    b.putInt("selectedMovie", selectedMovieId)
                    it.findNavController().navigate(R.id.action_categoriesFragment_to_movieDetailFragment,b)
                }
            }
        }
    }

}