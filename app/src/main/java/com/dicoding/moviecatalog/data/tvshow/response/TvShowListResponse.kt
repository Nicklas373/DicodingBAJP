package com.dicoding.moviecatalog.data.tvshow.response

import com.google.gson.annotations.SerializedName

data class TvShowListResponse(
    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("original_name")
    val originalName: String,

    @field:SerializedName("first_air_date")
    val releasedDate: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("poster_path")
    val posterPath: String
)

