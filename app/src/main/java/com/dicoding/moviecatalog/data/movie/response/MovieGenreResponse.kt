package com.dicoding.moviecatalog.data.movie.response

import com.google.gson.annotations.SerializedName

data class MovieGenreResponse(
    @field:SerializedName("genres")
    val genres: ArrayList<MovieGenreListResponse>
)