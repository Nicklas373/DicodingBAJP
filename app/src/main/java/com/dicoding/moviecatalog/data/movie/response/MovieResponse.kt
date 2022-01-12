package com.dicoding.moviecatalog.data.movie.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("items")
    val items: ArrayList<MovieListResponse>
)