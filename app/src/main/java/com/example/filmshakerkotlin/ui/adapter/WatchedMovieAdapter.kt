package com.example.filmshakerkotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.filmshakerkotlin.R
import com.example.filmshakerkotlin.data.local.watched.WatchedEntity
import com.example.filmshakerkotlin.databinding.WatchedItemResultCardBinding


class WatchedMovieAdapter(
    private val watchedList: ArrayList<WatchedEntity>,

    ) : RecyclerView.Adapter<WatchedMovieAdapter.WatchedViewHolder>() {
    class WatchedViewHolder(val binding: WatchedItemResultCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<WatchedItemResultCardBinding>(
            inflater,
            R.layout.watched_item_result_card,
            parent,
            false
        )
        return WatchedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return watchedList.size
    }

    override fun onBindViewHolder(holder: WatchedViewHolder, position: Int) {
        holder.binding.watched = watchedList[position]
//        holder.binding.afisImageButton.setOnClickListener {
//            val dialogFragment = RateFragment()
//            val fragmentManager = (it.context as FragmentActivity).supportFragmentManager
//            dialogFragment.show(fragmentManager, "RateFragment")
//        }

    }


}

