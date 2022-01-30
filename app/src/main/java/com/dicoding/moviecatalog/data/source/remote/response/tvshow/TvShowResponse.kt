package com.dicoding.moviecatalog.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

class TvShowResponse(
    @field:SerializedName("items")
    val items: List<TvShowListResponse>
)