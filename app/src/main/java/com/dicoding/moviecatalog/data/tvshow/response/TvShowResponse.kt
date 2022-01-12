package com.dicoding.moviecatalog.data.tvshow.response

import com.google.gson.annotations.SerializedName

class TvShowResponse(
    @field:SerializedName("items")
    val items: ArrayList<TvShowListResponse>
)