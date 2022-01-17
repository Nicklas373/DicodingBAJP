package com.dicoding.moviecatalog.data.movie.response

import com.google.gson.annotations.SerializedName

data class MovieGenreListResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String
)
