package com.dicoding.moviecatalog.data.tvshow

data class TvShowEntity(
    val tvShowId: String,
    val title: String,
    val description: String,
    val genre1: String,
    val genre2: String,
    val episode: String,
    val season: String,
    val duration: String,
    val releaseDate: String,
    val imagePath: String,
    val rating: String
)