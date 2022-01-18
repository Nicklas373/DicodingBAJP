package com.dicoding.moviecatalog.data.tvshow.response

import com.google.gson.annotations.SerializedName

data class TvShowGenreResponse(
    @field:SerializedName("genres")
    val genres: ArrayList<TvShowGenreListResponse>
)