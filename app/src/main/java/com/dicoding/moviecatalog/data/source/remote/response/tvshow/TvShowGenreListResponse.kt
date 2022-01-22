package com.dicoding.moviecatalog.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowGenreListResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String
)