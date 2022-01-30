package com.dicoding.moviecatalog.data.source.remote.response.tvshow

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

data class TvShowProductionCompaniesListResponse(
    @field:SerializedName("id")
    val id: String,

    @Nullable
    @field:SerializedName("logo_path")
    val logoPath: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("origin_country")
    val originCountry: String
)