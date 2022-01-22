package com.dicoding.moviecatalog.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowGenreResponse(
    @field:SerializedName("genres")
    val genres: ArrayList<TvShowGenreListResponse>
)