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

class SoonMovieAdapter(
    private val soonList: ArrayList<MovieDetail>,
    private val fragmentManager: FragmentManager
) :
    RecyclerView.Adapter<SoonMovieAdapter.SoonViewHolder>() {
    class SoonViewHolder(val binding: ItemResultCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemResultCardBinding>(
            inflater,
            R.layout.item_result_card,
            parent,
            false
        )
        return SoonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return soonList.size
    }

    override fun onBindViewHolder(holder: SoonViewHolder, position: Int) {
        holder.binding.soon = soonList[position]
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