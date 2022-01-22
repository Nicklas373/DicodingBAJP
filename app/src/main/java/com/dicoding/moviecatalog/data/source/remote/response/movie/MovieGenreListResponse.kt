package com.dicoding.moviecatalog.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieGenreListResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String
)
