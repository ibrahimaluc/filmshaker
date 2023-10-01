package com.example.filmshakerkotlin.ui.screen.watched

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.filmshakerkotlin.R
import com.example.filmshakerkotlin.data.local.watched.WatchedEntity
import com.example.filmshakerkotlin.databinding.WatchedItemResultCardBinding
import com.example.filmshakerkotlin.ui.screen.RateFragment
import kotlin.coroutines.coroutineContext


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

    override fun onBindViewHolder(holder: WatchedMovieAdapter.WatchedViewHolder, position: Int) {
        holder.binding.watched = watchedList[position]
//        holder.binding.afisImageButton.setOnClickListener {
//            val dialogFragment = RateFragment()
//            val fragmentManager = (it.context as FragmentActivity).supportFragmentManager
//            dialogFragment.show(fragmentManager, "RateFragment")
//        }

    }


}

