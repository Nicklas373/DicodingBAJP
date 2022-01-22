package com.dicoding.moviecatalog.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("items")
    val items: ArrayList<MovieListResponse>
)