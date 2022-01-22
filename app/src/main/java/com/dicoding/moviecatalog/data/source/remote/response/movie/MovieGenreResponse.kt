package com.dicoding.moviecatalog.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieGenreResponse(
    @field:SerializedName("genres")
    val genres: ArrayList<MovieGenreListResponse>
)