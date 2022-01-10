package com.dicoding.moviecatalog.data.movie.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    var movieId: String,
    var title: String,
    var genre1: String,
    var genre2: String,
    var description: String,
    var duration: String,
    var releaseDate: String,
    var imagePath: String,
    var rating: String
) : Parcelable