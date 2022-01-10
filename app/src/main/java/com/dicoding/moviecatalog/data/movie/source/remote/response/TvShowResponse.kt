package com.dicoding.moviecatalog.data.movie.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowResponse(
    val tvShowId: String,
    val title: String,
    val description: String,
    val genre1: String,
    val genre2: String,
    val episode: String,
    val season: String,
    val duration: String,
    val releaseDate: String,
    val imagePath: String,
    val rating: String
) : Parcelable