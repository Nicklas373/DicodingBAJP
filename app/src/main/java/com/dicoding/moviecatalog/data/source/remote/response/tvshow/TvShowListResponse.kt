package com.dicoding.moviecatalog.data.source.remote.response.tvshow

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

data class TvShowListResponse(

    @field:SerializedName("first_air_date")
    val tvShowFirstAirDate: String,

    @field:SerializedName("id")
    val tvShowId: Int,

    @field:SerializedName("name")
    val tvShowName: String,

    @field:SerializedName("number_of_episodes")
    val tvShowEpisodes: Int,

    @field:SerializedName("number_of_seasons")
    val tvShowSeasons: Int,

    @field:SerializedName("original_language")
    val tvShowLanguage: String,

    @field:SerializedName("overview")
    val tvShowOverview: String,

    @field:SerializedName("poster_path")
    val tvShowPoster: String,

    @field:SerializedName("vote_average")
    val tvShowVote: Double,

    @field:SerializedName("popularity")
    val tvShowPopularity: String,

    @field:SerializedName("genres")
    val tvShowGenres: List<TvShowGenreListResponse>,

    @field:SerializedName("production_companies")
    val tvShowProductionCompanies: List<TvShowProductionCompaniesListResponse>,

    @Nullable
    @field:SerializedName("isSus")
    val isSus: Boolean = false
)

