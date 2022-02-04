package com.dicoding.moviecatalog.data.source.remote.response.movie

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("original_language")
    val originalLanguage: String,

    @field:SerializedName("revenue")
    val revenue: Int,

    @SerializedName("genres")
    val genres: List<MovieGenreListResponse>,

    @field:SerializedName("original_title")
    val originalTitle: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("production_companies")
    val productionCompanies: List<MovieProductionCompaniesListResponse>,

    @Nullable
    @field:SerializedName("isSus")
    val isSus: Boolean = false
)