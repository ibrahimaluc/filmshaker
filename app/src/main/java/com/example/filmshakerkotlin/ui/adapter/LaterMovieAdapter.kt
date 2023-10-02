package com.example.filmshakerkotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.filmshakerkotlin.R
import com.example.filmshakerkotlin.data.local.later.LaterEntity
import com.example.filmshakerkotlin.databinding.LaterItemResultCardBinding

class LaterMovieAdapter(
    private val watchedList: ArrayList<LaterEntity>,

    ) : RecyclerView.Adapter<LaterMovieAdapter.LaterViewHolder>() {
    class LaterViewHolder(val binding: LaterItemResultCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<LaterItemResultCardBinding>(
            inflater,
            R.layout.later_item_result_card,
            parent,
            false
        )
        return LaterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return watchedList.size
    }

    override fun onBindViewHolder(holder: LaterViewHolder, position: Int) {
        holder.binding.later = watchedList[position]


    }

}
