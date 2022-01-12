package com.dicoding.moviecatalog.data.movie.response

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @field:SerializedName("id")
    val movieId: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("original_title")
    val originalTitle: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val posterPath: String
)