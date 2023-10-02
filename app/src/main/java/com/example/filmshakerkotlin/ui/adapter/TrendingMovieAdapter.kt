package com.example.filmshakerkotlin.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.filmshakerkotlin.R
import com.example.filmshakerkotlin.databinding.TrendingItemResultCardBinding
import com.example.filmshakerkotlin.domain.model.trending.TrendingDetail

class TrendingMovieAdapter(
    private val trendingList: ArrayList<TrendingDetail>,
    private val fragmentManager: FragmentManager
) :
    RecyclerView.Adapter<TrendingMovieAdapter.TrendingViewHolder>() {
    class TrendingViewHolder(val binding: TrendingItemResultCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<TrendingItemResultCardBinding>(
            inflater,
            R.layout.trending_item_result_card,
            parent,
            false
        )
        return TrendingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trendingList.size
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.binding.trending = trendingList[position]
        holder.binding.trending?.let { movieDetail ->
            holder.binding.posterImageButton.setOnClickListener {
                val selectedMovieId: Int? = movieDetail.id
                if (selectedMovieId != null) {
                    val b = Bundle()
                    b.putInt("selectedMovie", selectedMovieId)
                    it.findNavController()
                        .navigate(R.id.action_categoriesFragment_to_movieDetailFragment, b)
                }
            }
        }

    }


}