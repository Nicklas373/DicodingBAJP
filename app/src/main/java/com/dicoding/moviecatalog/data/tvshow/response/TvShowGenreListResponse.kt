package com.dicoding.moviecatalog.data.tvshow.response

import com.google.gson.annotations.SerializedName

data class TvShowGenreListResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String
)