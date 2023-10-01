package com.example.filmshakerkotlin.core.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("android:getImage")
fun getImage(view: ImageView, imageUri: String?) {
    val baseUrl : String ="https://image.tmdb.org/t/p/w500"
    val placeholder: CircularProgressDrawable = CircularProgressDrawable(view.context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
    val options = RequestOptions()
        .placeholder(placeholder)

    Glide.with(view)
        .setDefaultRequestOptions(options)
        .load(baseUrl+imageUri)
        .centerInside()
        .into(view)
}