package com.example.filmshakerkotlin.core.adapter

import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.filmshakerkotlin.domain.model.MovieGenre
import com.example.filmshakerkotlin.domain.model.alldetail.GenreTypes
import java.text.NumberFormat
import java.util.Locale


@BindingAdapter("android:rating")
fun setRating(view: RatingBar, rating: Double) {
    view.rating = (rating / 2).toFloat()
}

fun List<Int?>?.toMovieGenres(): List<MovieGenre>? {
    return this?.mapNotNull { MovieGenre.fromId(it ?: return@mapNotNull null) }
}

@BindingAdapter("android:genreIdsToString")
fun TextView.genreIdsToString(genreIds: List<Int>?) {
    val genres = genreIds?.toMovieGenres()
    if (genres == null) {
        text = ""
        return
    }
    val genreDescriptions = genres.map { it.description }
    text = genreDescriptions.joinToString(", ")
}


@BindingAdapter("android:progressWithMax")
fun setProgressWithMax(seekBar: SeekBar, ratingValue: Float) {
    val max = 100
    val progress = ((ratingValue / 10) * max).toInt()
    seekBar.max = max
    seekBar.progress = progress
}

@BindingAdapter("android:onlyYear")
fun setYear(textView: TextView, date: String?) {
    val year = date?.substring(0, 4)
    textView.text = year
}
@BindingAdapter("android:formattedVoteAverage")
fun setFormattedVoteAverage(textView: TextView, voteAverage: Double) {
    val formattedVoteAverage = String.format("%.1f", voteAverage)
    textView.text = formattedVoteAverage
}

@BindingAdapter("android:genreText")
fun setGenreListText(textView: TextView, genreList: List<GenreTypes>?) {
    genreList?.let {
        val genreText = it.joinToString(", ") { genre -> genre.name ?: "" }
        textView.text = genreText
    }
}

@BindingAdapter("android:revenueFormatted")
fun TextView.setFormattedRevenue(revenue: Int) {
    val revenueLong = revenue.toLong()
    val formattedRevenue = NumberFormat.getCurrencyInstance(Locale.US).format(revenueLong)
    text = formattedRevenue
}


