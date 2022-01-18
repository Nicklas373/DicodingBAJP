package com.dicoding.moviecatalog.data.tvshow.response

import com.google.gson.annotations.SerializedName

data class TvShowDetailResponse(
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
    val tvShowPopularity: String
)